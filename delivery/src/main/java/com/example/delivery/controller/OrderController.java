package com.example.delivery.controller;

import com.example.delivery.dto.CreateOrderRequest;
import com.example.delivery.dto.OrderResponse;
import com.example.delivery.facade.OrderFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST-контроллер для работы с заказами.
 * <p>
 * Обрабатывает HTTP-запросы, связанные с созданием заказов.
 * Все запросы начинаются с пути "/api/orders".
 * </p>
 */
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderFacade orderFacade;

    /**
     * Создаёт новый заказ на основе данных из запроса.
     *
     * @param request объект с данными для создания заказа {@link CreateOrderRequest}
     * @return HTTP-ответ с созданным заказом {@link OrderResponse} и статусом 200 OK
     */
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        return ResponseEntity.ok(orderFacade.createOrder(request));
    }
}
