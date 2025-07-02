package ru.itone.jun2middle.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itone.jun2middle.shop.model.entity.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    boolean existsById(UUID id);
    Optional<Product> findById(UUID id);
}