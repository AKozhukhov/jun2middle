package ru.itone.jun2middle.shop.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itone.jun2middle.shop.model.dto.order.CreatedOrderDto;
import ru.itone.jun2middle.shop.model.dto.order.DeliveryOrderRequest;
import ru.itone.jun2middle.shop.model.dto.order.OrderDto;
import ru.itone.jun2middle.shop.model.dto.order.ReserveOrderRequest;
import ru.itone.jun2middle.shop.model.entity.Order;
import ru.itone.jun2middle.shop.model.entity.User;
import ru.itone.jun2middle.shop.model.entity.enums.Status;
import ru.itone.jun2middle.shop.service.ProductService;
import ru.itone.jun2middle.shop.service.UserService;

/**
 * Класс для преобразования Entity и DTO Order
 */
@Service
@RequiredArgsConstructor
public class OrderMapper {

    private final ProductService productService;
    private final UserService userService;

    /**
     * Преобразование OrderDto в Order
     *
     * @param orderDto Объект OrderDto
     * @return Order (Entity)
     */
    public Order toEntity(OrderDto orderDto) {
        return new Order(
                null,
                orderDto.getProductId(),
                orderDto.getUserId(),
                Status.NEW,
                null);
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
                .productId(order.getProductId())
                .userId(order.getUserId())
                .status(order.getStatus())
                .description(order.getDescription())
                .build();
    }

    /**
     * Формирование ReserveOrderRequest из Order
     *
     * @param order Order (Entity)
     * @return Объект ReserveOrderRequest
     */
    public ReserveOrderRequest toReserveOrderRequest(Order order) {
        return ReserveOrderRequest.builder()
                .shopOrderId(order.getId().toString())
                .productId(order.getProductId().toString())
                .build();
    }

    /**
     * Формирование DeliveryOrderRequest из Order
     *
     * @param order Order (Entity)
     * @return Объект DeliveryOrderRequest
     */
    public DeliveryOrderRequest toDeliveryOrderRequest(Order order) {
        User user = userService.getUserById(order.getUserId());
        return DeliveryOrderRequest.builder()
                .shopOrderId(order.getId())
                .size(productService.getSizeById(order.getProductId()))
                .locationX(user.getLocationX())
                .locationY(user.getLocationY())
                .build();
    }
}
