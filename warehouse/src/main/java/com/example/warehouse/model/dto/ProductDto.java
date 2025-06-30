package com.example.warehouse.model.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductDto {

    String name;

    int size;

    int count;

}
