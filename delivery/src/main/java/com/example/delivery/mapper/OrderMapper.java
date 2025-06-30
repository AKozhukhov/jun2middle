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
    Order toEntity(CreateOrderRequest request);


    OrderResponse toDto(Order order);
}
