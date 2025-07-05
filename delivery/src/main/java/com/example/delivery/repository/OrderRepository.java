package com.example.delivery.repository;

import com.example.delivery.entity.Order;
import com.example.delivery.entity.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findByStatusOrderByCreateDateAsc(OrderStatus status);

    List<Order> findByStatusAndArrivalTimeLessThanEqual(OrderStatus orderStatus, LocalDateTime now);
}
