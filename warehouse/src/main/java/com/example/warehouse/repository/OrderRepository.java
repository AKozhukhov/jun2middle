package com.example.warehouse.repository;

import com.example.warehouse.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    /**
     * Найти заказ по идентификатору заказа в магазине.
     *
     * @param shopOrderId идентификатор заказа в магазине.
     * @return Optional<Order>, содержащий заказ, если он найден, или пустой, если заказ не найден
     */
    Optional<Order> findByShopOrderId(UUID shopOrderId);


    /**
     * Найти количество заказов со статусами 'NEW', 'DELIVERY'.
     *
     * @return long, количество
     */
    @Query("SELECT COUNT(*) FROM Order o WHERE o.status = 'NEW' OR o.status = 'DELIVERY'")
    long ordersInDelivery();

}
