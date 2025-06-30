package com.example.delivery.facade;

import com.example.delivery.dto.CreateOrderRequest;
import com.example.delivery.dto.OrderResponse;
import com.example.delivery.entity.Order;
import com.example.delivery.mapper.OrderMapper;
import com.example.delivery.service.OrderService;
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
public class OrderFacadeTest {

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderFacade orderFacade;

    @Test
    void createOrder_ShouldReturnCorrectResponse() {
        UUID shopOrderId = UUID.randomUUID();
        CreateOrderRequest request = new CreateOrderRequest(
                shopOrderId,
                2,
                100,
                200
        );

        Order entity = Order.builder()
                .shopOrderId(shopOrderId)
                .size(2)
                .locationX(100)
                .locationY(200)
                .build();

        UUID generatedId = UUID.randomUUID();
        LocalDateTime createDate = LocalDateTime.now();
        Order savedEntity = Order.builder()
                .id(generatedId)
                .shopOrderId(shopOrderId)
                .size(2)
                .createDate(createDate)
                .locationX(100)
                .locationY(200)
                .build();

        OrderResponse expectedResponse = new OrderResponse(
                generatedId,
                shopOrderId,
                2,
                100,
                200,
                createDate
        );

        when(orderMapper.toEntity(request)).thenReturn(entity);
        when(orderService.createOrder(entity)).thenReturn(savedEntity);
        when(orderMapper.toDto(savedEntity)).thenReturn(expectedResponse);

        OrderResponse actualResponse = orderFacade.createOrder(request);

        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(generatedId, actualResponse.id());
        Assertions.assertEquals(shopOrderId, actualResponse.shopOrderId());
        Assertions.assertEquals(2, actualResponse.size());
        Assertions.assertEquals(createDate, actualResponse.createDate());
        Assertions.assertEquals(100, actualResponse.locationX());
        Assertions.assertEquals(200, actualResponse.locationY());

        verify(orderMapper, times(1)).toEntity(request);
        verify(orderService, times(1)).createOrder(entity);
        verify(orderMapper, times(1)).toDto(savedEntity);
        verifyNoMoreInteractions(orderMapper, orderService);
    }
}
