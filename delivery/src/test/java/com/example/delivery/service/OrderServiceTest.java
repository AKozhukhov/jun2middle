package com.example.delivery.service;


import com.example.delivery.entity.Order;
import com.example.delivery.entity.enums.OrderStatus;
import com.example.delivery.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void createOrder_shouldSaveOrderSuccessfully() {
        Order order = createValidOrder();
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order result = orderService.createOrder(order);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(order.getId(), result.getId());
        Assertions.assertEquals(order.getShopOrderId(), result.getShopOrderId());
        Assertions.assertEquals(order.getSize(), result.getSize());
        Assertions.assertEquals(order.getStatus(), result.getStatus());
        verify(orderRepository, times(1)).save(order);
    }

    private Order createValidOrder() {
        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setShopOrderId(UUID.randomUUID());
        order.setSize(2);
        order.setCreateDate(LocalDateTime.now());
        order.setLocationX(100);
        order.setLocationY(200);
        order.setStatus(OrderStatus.NEW);
        return order;
    }
}
