package com.example.warehouse.facade;

import com.example.warehouse.mapper.OrderMapper;
import com.example.warehouse.model.dto.CreatedOrderDto;
import com.example.warehouse.model.dto.OrderDto;
import com.example.warehouse.model.entity.Order;
import com.example.warehouse.model.entity.Product;
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

        Optional<Product> optionalProduct = productService.findById(UUID.fromString(orderDto.getProductId()));
        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("Unknown product");
        }
        Product product = optionalProduct.get();
        if (product.getCount() == 0) {
            throw new RuntimeException("The product is out of stock");
        }
        product.setCount(product.getCount() - 1);

        Order order = orderMapper.dtoOrderToOrder(orderDto);
        Order saved = orderService.saveOrder(order);
        return orderMapper.orderToCreatedOrderDto(saved);
    }
}
