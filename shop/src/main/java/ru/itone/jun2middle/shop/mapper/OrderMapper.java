package ru.itone.jun2middle.shop.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itone.jun2middle.shop.model.dto.order.CreatedOrderDto;
import ru.itone.jun2middle.shop.model.dto.order.DeliveryOrderRequest;
import ru.itone.jun2middle.shop.model.dto.order.OrderDto;
import ru.itone.jun2middle.shop.model.dto.order.ReserveOrderRequest;
import ru.itone.jun2middle.shop.model.entity.Order;
import ru.itone.jun2middle.shop.model.entity.User;
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

    /**
     * Формирование ReserveOrderRequest из Order
     *
     * @param order Order (Entity)
     * @return Объект ReserveOrderRequest
     */
    public ReserveOrderRequest toReserveOrderRequest(Order order) {
        return ReserveOrderRequest.builder()
                .shop_order_id(order.getId().toString())
                .product_id(order.getProduct_id().toString())
                .build();
    }

    /**
     * Формирование DeliveryOrderRequest из Order
     *
     * @param order Order (Entity)
     * @return Объект DeliveryOrderRequest
     */
    public DeliveryOrderRequest toDeliveryOrderRequest(Order order) {
        User user = userService.getUserById(order.getUser_id());
        return DeliveryOrderRequest.builder()
                .shop_order_id(order.getId())
                .size(productService.getSizeById(order.getProduct_id()))
                .location_x(user.getLocation_x())
                .location_y(user.getLocation_y())
                .build();
    }
}
