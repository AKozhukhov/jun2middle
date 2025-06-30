package com.example.warehouse.model.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class CreatedOrderDto {

    UUID id;

    UUID shopOrderId;

    UUID productId;

}
