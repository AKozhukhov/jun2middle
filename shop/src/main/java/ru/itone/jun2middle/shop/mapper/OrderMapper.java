package ru.itone.jun2middle.shop.mapper;

import org.springframework.stereotype.Service;
import ru.itone.jun2middle.shop.model.dto.order.CreatedOrderDto;
import ru.itone.jun2middle.shop.model.dto.order.OrderDto;
import ru.itone.jun2middle.shop.model.entity.Order;

/**
 * Класс для преобразования Entity и DTO Order
 */
@Service
public class OrderMapper {

    /**
     * Преобразование OrderDto в Order
     *
     * @param orderDto Объект OrderDto
     * @return Order (Entity)
     */
    public Order toEntity(OrderDto orderDto) {
        return new Order(
                null,
                orderDto.getProduct_id(),
                orderDto.getUser_id());
    }

    /**
     * Преобразование Order в CreatedOrderDto
     *
     * @param order Order (Entity)
     * @return Объект CreatedOrderDto
     */
    public CreatedOrderDto toCreatedDto(Order order) {
        return CreatedOrderDto.builder()
                .id(order.getId())
                .product_id(order.getProduct_id())
                .user_id(order.getUser_id())
                .build();
    }
}
