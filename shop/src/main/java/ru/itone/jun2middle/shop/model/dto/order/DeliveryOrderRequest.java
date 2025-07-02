package ru.itone.jun2middle.shop.model.dto.order;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class DeliveryOrderRequest {
    UUID shopOrderId;
    int size;
    int locationX;
    int locationY;
}
