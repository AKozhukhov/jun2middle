package com.example.delivery.mapper;

import com.example.delivery.dto.CreateOrderRequest;
import com.example.delivery.dto.OrderResponse;
import com.example.delivery.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface  OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "arrivalTime", ignore = true)
    @Mapping(target = "courier", ignore = true)
    @Mapping(target = "status", constant = "NEW")
    Order toEntity(CreateOrderRequest request);

    @Mapping(target = "courierId", source = "courier.id")
    OrderResponse toDto(Order order);
}
