package ru.itone.jun2middle.shop.model.dto.user;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {
    String fio;
    String email;
    int location_x;
    int location_y;
}
