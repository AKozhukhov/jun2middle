package com.example.delivery.service;


import com.example.delivery.entity.Courier;
import com.example.delivery.entity.Order;
import com.example.delivery.entity.enums.OrderStatus;
import com.example.delivery.exception.InvalidCoordinatesException;
import com.example.delivery.repository.OrderRepository;
import com.example.delivery.utils.OrderCoordinateValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderDispatchService {

    private final OrderRepository orderRepository;
    private final CourierService courierService;
    private final OrderCoordinateValidator orderCoordinateValidator;

    @Scheduled(fixedRate = 10000)
    @Transactional
    public void dispatchOrders() {
        log.debug("Начало распределения заказов");

        List<Order> orders = getAllNewOrdersSorted();
        if (orders.isEmpty()) {
            log.debug("Нет заказов для распределения");
            return;
        }

        List<Courier> availableCouriers = courierService.getAvailableCouriers();
        if (availableCouriers.isEmpty()) {
            log.debug("Нет свободных курьеров");
            return;
        }

        Map<Integer, List<Courier>> capacityMap = createCapacityMap(availableCouriers);
        processOrders(orders, capacityMap);
    }

    private List<Order> getAllNewOrdersSorted() {
        return orderRepository.findByStatusOrderByCreateDateAsc(OrderStatus.NEW);
    }

    private Map<Integer, List<Courier>> createCapacityMap(List<Courier> couriers) {
        return couriers.stream()
                .collect(Collectors.groupingBy(
                        c -> c.getTransport().getCapacity(),
                        TreeMap::new,
                        Collectors.toCollection(LinkedList::new)
                ));
    }

    private void processOrders(List<Order> orders, Map<Integer, List<Courier>> capacityMap) {
        for (Order order : orders) {
            try {
                var distance =orderCoordinateValidator.validateAndCalculateDistance(order.getLocationX(),order.getLocationY());
                if (capacityMap.isEmpty()) {
                    log.debug("Все курьеры заняты, прерывание обработки");
                    break;
                }
                Optional<Courier> courierOpt = findSuitableCourier(order, capacityMap);
                courierOpt.ifPresentOrElse(courier -> assignOrder(order, courier, capacityMap,distance),
                        ()->log.debug("Нет курьера на заказ {} размером {}",order.getId(),order.getSize()));
            }catch (InvalidCoordinatesException e){
                order.setStatus(OrderStatus.ERROR);
                orderRepository.save(order);
                log.error("Координаты заказа {} вне зоны доставки: {}",
                        order.getId(), e.getMessage());
            }
            catch (Exception e) {
                log.error("Ошибка при обработке заказа {}", order.getId(), e);
            }
        }
    }

    private Optional<Courier> findSuitableCourier(Order order, Map<Integer, List<Courier>> capacityMap) {
        return capacityMap.keySet().stream()
                .filter(capacity -> capacity >= order.getSize())
                .min(Integer::compare)
                .flatMap(capacity -> getFirstCourier(capacityMap, capacity));
    }

    private Optional<Courier> getFirstCourier(Map<Integer, List<Courier>> capacityMap, Integer capacity) {
        List<Courier> couriers = capacityMap.get(capacity);
        if (couriers != null && !couriers.isEmpty()) {
            return Optional.of(couriers.get(0));
        }
        return Optional.empty();
    }

    private void assignOrder(Order order, Courier courier, Map<Integer, List<Courier>> capacityMap, int distance) {
        int capacity = courier.getTransport().getCapacity();
        var deliverySeconds = (int) Math.ceil((double) distance / courier.getTransport().getSpeed());

        removeCourierFromMap(capacityMap, capacity, courier);
        courierService.assignOrderToCourier(courier,deliverySeconds);
        order.setStatus(OrderStatus.DELIVERY);
        order.setCourier(courier);
        order.setArrivalTime(LocalDateTime.now().plusSeconds(deliverySeconds));
        orderRepository.save(order);

        log.info("curierId={}, transport={}, time={}s",
                courier.getId(), courier.getTransport(), deliverySeconds);
        log.info("Заказ {} (размер={}) назначен курьеру {} (транспорт={})",
                order.getId(), order.getSize(), courier.getId(), courier.getTransport());
    }

    private void removeCourierFromMap(Map<Integer, List<Courier>> capacityMap, int capacity, Courier courier) {
        List<Courier> couriers = capacityMap.get(capacity);
        if (couriers != null) {
            couriers.remove(courier);
            if (couriers.isEmpty()) {
                capacityMap.remove(capacity);
            }
        }
    }
}

