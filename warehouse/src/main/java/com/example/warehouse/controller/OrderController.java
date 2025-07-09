package com.example.warehouse.controller;

import com.example.warehouse.facade.OrderFacade;
import com.example.warehouse.model.dto.CreatedOrderDto;
import com.example.warehouse.model.dto.OrderDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
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
    @PostMapping("/new")
    public CreatedOrderDto reserveOrder(@Valid @RequestBody OrderDto orderDto) {
        return orderFacade.reserveOrder(orderDto);
    }

    /**
     * Доставка забирает заказ в работу
     * @param shopOrderId идентификатор заказа в магазине
     * @return CreatedOrderDto
     */
    @PutMapping("/delivery")
    public CreatedOrderDto orderToDelivery(@NotBlank @RequestParam String shopOrderId) {
        return orderFacade.orderToDelivery(shopOrderId);
    }

    /**
     * Доставка успешно завершается
     * @param shopOrderId идентификатор заказа в магазине
     * @return CreatedOrderDto
     */
    @PutMapping("/success")
    public CreatedOrderDto orderToSuccess(@NotBlank @RequestParam String shopOrderId) {
        return orderFacade.orderToSuccess(shopOrderId);
    }

    /**
     * Ошибка при доставке (возврат товара)
     * @param shopOrderId идентификатор заказа в магазине
     * @return CreatedOrderDto
     */
    @PutMapping("/error")
    public CreatedOrderDto orderToError(@NotBlank @RequestParam String shopOrderId) {
        return orderFacade.orderToError(shopOrderId);
    }

}
