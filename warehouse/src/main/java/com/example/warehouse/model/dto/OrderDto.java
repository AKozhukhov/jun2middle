package com.example.warehouse.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class OrderDto {

    @NotNull
    UUID shopOrderId;

    @NotNull
    UUID productId;

}
