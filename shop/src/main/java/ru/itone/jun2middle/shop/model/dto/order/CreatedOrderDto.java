package ru.itone.jun2middle.shop.model.dto.order;

import lombok.Builder;
import lombok.Value;
import ru.itone.jun2middle.shop.model.entity.enums.Status;

import java.util.UUID;

@Value
@Builder
public class CreatedOrderDto {
    UUID id;
    UUID productId;
    UUID userId;
    Status status;
    String description;
}
