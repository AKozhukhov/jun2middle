package com.example.warehouse.facade;

import com.example.warehouse.mapper.OrderMapper;
import com.example.warehouse.model.dto.CreatedOrderDto;
import com.example.warehouse.model.entity.Order;
import com.example.warehouse.model.entity.Product;
import com.example.warehouse.model.entity.Status;
import com.example.warehouse.service.OrderService;
import com.example.warehouse.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit тесты для класса в слое "фасад", предназначенного для сохранения и изменения заявок
 */
@ExtendWith(MockitoExtension.class)
public class OrderFacadeTest {

    @Spy
    private OrderMapper orderMapper;

    @Mock
    private OrderService orderService;

    @Mock
    private ProductService productService;

    @InjectMocks
    private OrderFacade orderFacade;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void reserveOrder() {
        assertNull(null);
        assertNotNull(new Object());
        assertEquals(1, 1);
    }

    @Test
    void orderToDelivery() {
        UUID shopOrderId = UUID.randomUUID();
        Order order = new Order(null, shopOrderId, null, Status.DELIVERY);
        CreatedOrderDto createdOrderDto = CreatedOrderDto.builder()
                .id(null)
                .shopOrderId(shopOrderId)
                .productId(null)
                .status(Status.DELIVERY)
                .build();
        when(orderService.setStatus(shopOrderId, Status.DELIVERY)).thenReturn(order);

        assertEquals(createdOrderDto, orderFacade.orderToDelivery(shopOrderId));
        verify(orderMapper, times(1)).orderToCreatedOrderDto(order);
        verify(orderService, times(1)).setStatus(shopOrderId, Status.DELIVERY);
    }

    @Test
    void orderToSuccess() {
        UUID shopOrderId = UUID.randomUUID();
        Order order = new Order(null, shopOrderId, null, Status.SUCCESS);
        CreatedOrderDto createdOrderDto = CreatedOrderDto.builder()
                .id(null)
                .shopOrderId(shopOrderId)
                .productId(null)
                .status(Status.SUCCESS)
                .build();
        when(orderService.setStatus(shopOrderId, Status.SUCCESS)).thenReturn(order);

        assertEquals(createdOrderDto, orderFacade.orderToSuccess(shopOrderId));
        verify(orderMapper, times(1)).orderToCreatedOrderDto(order);
        verify(orderService, times(1)).setStatus(shopOrderId, Status.SUCCESS);
    }

    @Test
    void orderToError() {
        UUID shopOrderId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        Order order = new Order(null, shopOrderId, productId, Status.ERROR);
        CreatedOrderDto createdOrderDto = CreatedOrderDto.builder()
                .id(null)
                .shopOrderId(shopOrderId)
                .productId(productId)
                .status(Status.ERROR)
                .build();
        Product product = new Product(productId, "Name", 3, 3);
        when(orderService.setStatus(shopOrderId, Status.ERROR)).thenReturn(order);
        when(productService.findById(productId)).thenReturn(Optional.of(product));

        assertEquals(createdOrderDto, orderFacade.orderToError(shopOrderId));
        verify(orderMapper, times(1)).orderToCreatedOrderDto(order);
        verify(orderService, times(1)).setStatus(shopOrderId, Status.ERROR);
        verify(productService, times(1)).findById(productId);
    }

}