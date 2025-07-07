package com.example.warehouse.service;

import com.example.warehouse.exception.OrderException;
import com.example.warehouse.model.entity.Order;
import com.example.warehouse.model.entity.Status;
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
        Status oldStatus = order.getStatus();
        if ((oldStatus == Status.NEW && newStatus != Status.DELIVERY)
                || (newStatus == Status.SUCCESS && oldStatus != Status.DELIVERY)
                || (newStatus == Status.ERROR && oldStatus != Status.DELIVERY)) {
            throw new OrderException("Error setStatus: %s -> %s".formatted(oldStatus, newStatus));
        }
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }

}
