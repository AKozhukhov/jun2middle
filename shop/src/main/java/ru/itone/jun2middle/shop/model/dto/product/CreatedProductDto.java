package ru.itone.jun2middle.shop.model.dto.product;

import lombok.Builder;
import lombok.Value;
import java.util.UUID;

@Value
@Builder
public class CreatedProductDto {
    UUID id;
    UUID warehouseId;
    String name;
    int size;
}
