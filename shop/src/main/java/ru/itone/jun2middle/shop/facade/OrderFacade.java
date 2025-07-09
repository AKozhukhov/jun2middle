package ru.itone.jun2middle.shop.facade;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.itone.jun2middle.shop.mapper.OrderMapper;
import ru.itone.jun2middle.shop.model.dto.order.CreatedOrderDto;
import ru.itone.jun2middle.shop.model.dto.order.DeliveryOrderRequest;
import ru.itone.jun2middle.shop.model.dto.order.OrderDto;
import ru.itone.jun2middle.shop.model.dto.order.ReserveOrderRequest;
import ru.itone.jun2middle.shop.model.entity.Order;
import ru.itone.jun2middle.shop.model.entity.enums.Status;
import ru.itone.jun2middle.shop.service.OrderService;

import java.nio.channels.ClosedChannelException;

@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Value("${shop.orders.warehouse_url}")
    private String warehouseURL;
    @Value("${shop.orders.delivery_url}")
    private String deliveryURL;

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

        try {
            ResponseEntity<Void> response = restClient.post()
                    .uri(warehouseURL)
                    .body(reserveOrderRequest)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError,(req,resp)->{
                        orderService.setError(createdOrder,"Ошибка резервации заказа на складе");
                    })
                    .toBodilessEntity();

            try {
                orderService.changeStatus(createdOrder, Status.DELIVERY);
                DeliveryOrderRequest deliveryOrderRequest = orderMapper.toDeliveryOrderRequest(createdOrder);
                response = restClient.post()
                        .uri(deliveryURL)
                        .body(deliveryOrderRequest)
                        .retrieve()
                        .onStatus(HttpStatusCode::isError, (req, resp) -> {
                            orderService.setError(createdOrder, "Ошибка при передаче заказа службе доставки");
                        })
                        .toBodilessEntity();
                orderService.changeStatus(createdOrder,Status.SUCCESS);
            } catch (RuntimeException e) {
                orderService.setError(createdOrder,"Ошибка при попытке соединения со службой доставки");
            }

        }
        catch (RuntimeException e) {
            orderService.setError(createdOrder,"Ошибка при попытке соединения со складом");
        }

        return orderMapper.toCreatedDto(createdOrder);
    }
}
