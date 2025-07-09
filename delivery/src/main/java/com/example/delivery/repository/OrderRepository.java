package com.example.delivery.repository;

import com.example.delivery.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findAllByOrderByCreateDateAsc();
}
