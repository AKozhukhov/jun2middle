package com.example.warehouse.model.dto;

import com.example.warehouse.model.entity.Status;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class CreatedOrderDto {

    UUID id;

    UUID shopOrderId;

    UUID productId;

    Status status;

}
