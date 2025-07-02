package ru.itone.jun2middle.shop.model.dto.order;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ReserveOrderRequest {
    String shopOrderId;
    String productId;
}
