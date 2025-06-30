package com.example.warehouse.service;

import com.example.warehouse.exception.OrderException;
import com.example.warehouse.model.entity.Order;
import com.example.warehouse.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Transactional
    public Order orderToDeliver(UUID shopOrderId) {
        Optional<Order> optionalOrder = orderRepository.findByShopOrderId(shopOrderId);
        if (optionalOrder.isEmpty()) {
            throw new OrderException("Unknown order");
        }
        Order delivered = optionalOrder.get();
        orderRepository.delete(delivered);
        return delivered;
    }
}
