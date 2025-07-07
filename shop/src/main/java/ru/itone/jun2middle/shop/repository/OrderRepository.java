package ru.itone.jun2middle.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itone.jun2middle.shop.model.entity.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    public Optional<Order> findById(UUID id);
}