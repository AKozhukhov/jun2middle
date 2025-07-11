package com.example.warehouse.model.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class ShopProductRequest {
    UUID warehouseId;
    String name;
    int size;
}
