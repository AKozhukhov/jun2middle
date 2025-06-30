package com.example.warehouse.service;

import com.example.warehouse.model.entity.Order;
import com.example.warehouse.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}
