package ru.itone.jun2middle.shop.facade;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.itone.jun2middle.shop.mapper.OrderMapper;
import ru.itone.jun2middle.shop.model.dto.order.CreatedOrderDto;
import ru.itone.jun2middle.shop.model.dto.order.DeliveryOrderRequest;
import ru.itone.jun2middle.shop.model.dto.order.OrderDto;
import ru.itone.jun2middle.shop.model.dto.order.ReserveOrderRequest;
import ru.itone.jun2middle.shop.model.entity.Order;
import ru.itone.jun2middle.shop.service.OrderService;

@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    private final String warehouseURL = "http://127.0.0.1:7055/api/v1/order";
    private final String deliveryURL = "http://127.0.0.1:7050/api/v1/orders";

    /**
     * Создание заказа
     *
     * @param orderDto Объект OrderDto
     * @return CreatedOrderDto
     */
    @Transactional
    public CreatedOrderDto create (OrderDto orderDto) {

        Order order = orderMapper.toEntity(orderDto);
        Order createdOrder = orderService.create(order);
        ReserveOrderRequest reserveOrderRequest = orderMapper.toReserveOrderRequest(createdOrder);
        RestClient restClient = RestClient.create();

        ResponseEntity<Void> response = restClient.post()
                .uri(warehouseURL)
                .body(reserveOrderRequest)
                .retrieve()
                .toBodilessEntity();
        if (response.getStatusCode().is2xxSuccessful()) {
            DeliveryOrderRequest deliveryOrderRequest = orderMapper.toDeliveryOrderRequest(createdOrder);
            response = restClient.post()
                    .uri(deliveryURL)
                    .body(deliveryOrderRequest)
                    .retrieve()
                    .toBodilessEntity();

            if (response.getStatusCode().is2xxSuccessful()) {
                return orderMapper.toCreatedDto(createdOrder);
            }
            else {
                orderService.delete(createdOrder);
                throw new RuntimeException("Ошибка запроса в службу доставки");
            }
        }
        else {
            orderService.delete(createdOrder);
            throw new RuntimeException("Ошибка резервации заказа на складе");
        }
    }


}
