package com.example.delivery.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderResponse(
        UUID id,
        UUID shopOrderId,
        int size,
        int locationX,
        int locationY,
        LocalDateTime createDate
) {
}
