package com.example.warehouse.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderDto {

    @NotBlank
    String shopOrderId;

    @NotBlank
    String productId;

}
