package com.example.warehouse.mapper;

import com.example.warehouse.model.dto.CreatedOrderDto;
import com.example.warehouse.model.dto.OrderDto;
import com.example.warehouse.model.entity.Order;
import com.example.warehouse.model.entity.Status;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderMapper {

    public Order dtoOrderToNewOrder(OrderDto orderDto) {
        return new Order(
                null,
                orderDto.getShopOrderId(),
                orderDto.getProductId(),
                Status.NEW);
    }

    public CreatedOrderDto orderToCreatedOrderDto(Order order) {
        return CreatedOrderDto.builder()
                .id(order.getId())
                .shopOrderId(order.getShopOrderId())
                .productId(order.getProductId())
                .status(order.getStatus())
                .build();
    }

}
