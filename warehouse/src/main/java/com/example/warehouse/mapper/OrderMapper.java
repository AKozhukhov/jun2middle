package com.example.warehouse.mapper;

import com.example.warehouse.model.dto.CreatedOrderDto;
import com.example.warehouse.model.dto.OrderDto;
import com.example.warehouse.model.entity.Order;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderMapper {

    public Order dtoOrderToOrder(OrderDto orderDto) {
        return new Order(
                null,
                UUID.fromString(orderDto.getShopOrderId()),
                UUID.fromString(orderDto.getProductId()));
    }

    public CreatedOrderDto orderToCreatedOrderDto(Order order) {
        return CreatedOrderDto.builder()
                .id(order.getId())
                .shopOrderId(order.getShopOrderId())
                .productId(order.getProductId())
                .build();
    }

}
