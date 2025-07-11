package com.example.warehouse.service;

import com.example.warehouse.repository.OrderRepository;
import com.example.warehouse.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FillingService {

    @Value("${app.full_filling_warehouse}")
    private int fullFilling;

    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;

    public long freeSpaceInWarehouse() {
        long productsInWarehouse = productRepository.productsNumber();
        long ordersInDelivery = orderRepository.ordersInDelivery();
        return fullFilling - productsInWarehouse - ordersInDelivery;
    }

}
