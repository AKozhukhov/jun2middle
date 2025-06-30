package com.example.warehouse.model.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderDto {

    String shopOrderId;

    String productId;

}
