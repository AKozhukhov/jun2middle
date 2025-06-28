package ru.itone.jun2middle.shop.model.dto.user;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class CreatedUserDto {
    UUID id;
    String fio;
    String email;
    int location_x;
    int location_y;
}
