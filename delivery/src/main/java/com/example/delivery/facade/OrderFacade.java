package com.example.delivery.facade;

import com.example.delivery.dto.CreateOrderRequest;
import com.example.delivery.dto.OrderResponse;
import com.example.delivery.mapper.OrderMapper;
import com.example.delivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderMapper orderMapper;
    private final OrderService orderService;
    
    
    public OrderResponse createOrder(CreateOrderRequest orderRequest){
        var order =orderMapper.toEntity(orderRequest);
        order =orderService.createOrder(order);
        return orderMapper.toDto(order);
    }
}
