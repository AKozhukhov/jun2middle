package ru.itone.jun2middle.shop.model.dto.test;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class CreatedTestDto {
    Long id;
    String var1;
    UUID var2;
}
