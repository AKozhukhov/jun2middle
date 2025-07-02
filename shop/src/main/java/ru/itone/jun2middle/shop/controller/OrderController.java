package ru.itone.jun2middle.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itone.jun2middle.shop.facade.OrderFacade;
import ru.itone.jun2middle.shop.model.dto.order.CreatedOrderDto;
import ru.itone.jun2middle.shop.model.dto.order.OrderDto;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderFacade orderFacade;

    /**
     * Создание заказа
     *
     * @param orderDto Объект OrderDto
     * @return CreatedOrderDto
     */
    @PostMapping
    public CreatedOrderDto create (@RequestBody OrderDto orderDto) {
        return orderFacade.create(orderDto);
    }
}
