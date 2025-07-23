package com.example.warehouse.repository;

import com.example.warehouse.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    /**
     * Найти продукт на складе по названию.
     *
     * @param name название продукта.
     * @return Optional<Product>, содержащий продукт, если он найден, или пустой, если не найден
     */
    Optional<Product> findByName(String name);

    /**
     * Найти общее количество продуктов на складе.
     *
     * @return long, количество
     */
    @Query("SELECT SUM(p.count * p.size) FROM Product p")
    long productsNumber();

}
