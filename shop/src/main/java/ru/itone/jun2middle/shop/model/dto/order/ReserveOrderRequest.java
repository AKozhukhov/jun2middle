package ru.itone.jun2middle.shop.model.dto.order;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class ReserveOrderRequest {
    String shop_order_id;
    String product_id;
}
