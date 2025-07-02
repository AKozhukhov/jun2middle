package com.example.warehouse.controller;

import com.example.warehouse.facade.OrderFacade;
import com.example.warehouse.model.dto.CreatedOrderDto;
import com.example.warehouse.model.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderFacade orderFacade;

    /**
     * Создание заказа
     * Если такого product_id нет в БД - ошибка - Unknown product
     * Если количество товара с product_id равно 0 - ошибка - The product is out of stock
     * @param orderDto с полями (shop_order_id, product_id)
     * @return CreatedOrderDto
     */
    @PostMapping
    public CreatedOrderDto reserveOrder(@RequestBody OrderDto orderDto) {
        return orderFacade.reserveOrder(orderDto);
    }

    /**
     * Доставка забирает заказ (он удаляется из склада)
     * @param shopOrderId идентификатор заказа в магазине
     * @return CreatedOrderDto
     */
    @DeleteMapping
    public CreatedOrderDto orderToDeliver(@RequestParam String shopOrderId) {
        return orderFacade.orderToDeliver(shopOrderId);
    }

}
