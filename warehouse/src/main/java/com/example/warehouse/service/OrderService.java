package com.example.warehouse.service;

import com.example.warehouse.exception.OrderException;
import com.example.warehouse.model.entity.Order;
import com.example.warehouse.model.entity.Status;
import com.example.warehouse.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Transactional
    public Order setStatus(UUID shopOrderId, Status newStatus) {
        Optional<Order> optionalOrder = orderRepository.findByShopOrderId(shopOrderId);
        if (optionalOrder.isEmpty()) {
            throw new OrderException("Unknown order by shopOrderId = %s".formatted(shopOrderId));
        }
        Order order = optionalOrder.get();
        checkCorrectStatusSwitch(order.getStatus(), newStatus);
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }

    private void checkCorrectStatusSwitch(Status oldStatus, Status newStatus) {
        if (oldStatus == Status.NEW && newStatus != Status.DELIVERY) {
            throw new OrderException("Error setStatus: %s -> %s".formatted(oldStatus, newStatus));
        }
        if (newStatus == Status.SUCCESS && oldStatus != Status.DELIVERY) {
            throw new OrderException("Error setStatus: %s -> %s".formatted(oldStatus, newStatus));
        }
        if (newStatus == Status.ERROR && oldStatus != Status.DELIVERY) {
            throw new OrderException("Error setStatus: %s -> %s".formatted(oldStatus, newStatus));
        }
    }

}
