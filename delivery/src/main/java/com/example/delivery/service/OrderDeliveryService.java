package com.example.delivery.service;

import com.example.delivery.entity.Order;
import com.example.delivery.entity.enums.OrderStatus;
import com.example.delivery.repository.CourierRepository;
import com.example.delivery.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderDeliveryService {

    private final OrderRepository orderRepository;
    private final AvailableService availableService;
    private final CourierRepository courierRepository;

    @Scheduled(fixedRate = 1000)
    @Transactional
    public void processDeliveries() {
        List<Order> deliveringOrders = orderRepository
                .findByStatusAndArrivalTimeLessThanEqual(OrderStatus.DELIVERY, LocalDateTime.now());

        for (Order order : deliveringOrders) {
            if (availableService.isRecipientAvailable()) {
                order.setStatus(OrderStatus.SUCCESS);
                log.info("Заказ {} успешно доставлен", order.getId());
            } else {
                order.setStatus(OrderStatus.ERROR);
                log.info("Получатель заказа {} недоступен", order.getId());
            }
            orderRepository.save(order);
            var courier = order.getCourier();
            courier.clearDelivery();
            courierRepository.save(courier);
        }
    }
}
