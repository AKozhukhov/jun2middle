package com.example.delivery.service;

import com.example.delivery.entity.Order;
import com.example.delivery.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public Order createOrder(Order order){
        log.debug("Получен запрос на создание заказа {}",order.toString());
        order = orderRepository.save(order );
        log.info("Заказ {} добавлен.",order.getShopOrderId());
        return order;
    }
}
