package com.example.warehouse.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductDto {

    @NotBlank
    String name;

    @NotNull
    @Min(value = 1)
    @Max(value = 3)
    Integer size;

    @NotNull
    @Positive
    Integer count;

}
