package com.example.delivery.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;
@NotNull
public record CreateOrderRequest(

        UUID shopOrderId,
        @Min(value = 1, message = "Размер должен быть от 1 до 3") @Max(value = 3, message = "Размер должен быть от 1 до 3")
        int size,
        int locationX,
        int locationY
) {
}
