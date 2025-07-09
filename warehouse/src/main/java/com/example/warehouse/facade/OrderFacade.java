package com.example.warehouse.facade;

import com.example.warehouse.exception.OrderException;
import com.example.warehouse.mapper.OrderMapper;
import com.example.warehouse.model.dto.CreatedOrderDto;
import com.example.warehouse.model.dto.OrderDto;
import com.example.warehouse.model.entity.Order;
import com.example.warehouse.model.entity.Product;
import com.example.warehouse.model.entity.Status;
import com.example.warehouse.service.OrderService;
import com.example.warehouse.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderMapper orderMapper;

    private final OrderService orderService;

    private final ProductService productService;

    @Transactional
    public CreatedOrderDto reserveOrder(OrderDto orderDto) {
        UUID productId = UUID.fromString(orderDto.getProductId());
        Optional<Product> optionalProduct = productService.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new OrderException("Unknown product by productId = %s".formatted(productId));
        }
        Product product = optionalProduct.get();
        if (product.getCount() == 0) {
            throw new OrderException("The product is out of stock, productId = %s".formatted(productId));
        }
        product.setCount(product.getCount() - 1);

        Order order = orderMapper.dtoOrderToNewOrder(orderDto);
        orderService.saveOrder(order);
        return orderMapper.orderToCreatedOrderDto(order);
    }

    public CreatedOrderDto orderToDelivery(String shopOrderId) {
        Order order = orderService.setStatus(UUID.fromString(shopOrderId), Status.DELIVERY);
        return orderMapper.orderToCreatedOrderDto(order);
    }

    public CreatedOrderDto orderToSuccess(String shopOrderId) {
        Order order = orderService.setStatus(UUID.fromString(shopOrderId), Status.SUCCESS);
        return orderMapper.orderToCreatedOrderDto(order);
    }

    @Transactional
    public CreatedOrderDto orderToError(String shopOrderId) {
        Order order = orderService.setStatus(UUID.fromString(shopOrderId), Status.ERROR);
        Product product = productService.findById(order.getProductId()).get();
        product.setCount(product.getCount() + 1);
        return orderMapper.orderToCreatedOrderDto(order);
    }
}
