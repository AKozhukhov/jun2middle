package com.example.warehouse.model.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class CreatedProductDto {

    UUID id;

    String name;

    int size;

    int count;

}
