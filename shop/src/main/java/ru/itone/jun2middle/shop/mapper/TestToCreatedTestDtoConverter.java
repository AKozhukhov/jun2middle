package ru.itone.jun2middle.shop.mapper;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import ru.itone.jun2middle.shop.model.dto.test.CreatedTestDto;
import ru.itone.jun2middle.shop.model.entity.Test;

@Service
public class TestToCreatedTestDtoConverter implements Converter<Test, CreatedTestDto> {
    @Override
    public CreatedTestDto convert(Test source) {
        return CreatedTestDto.builder()
                .id(source.getId())
                .var1(source.getVar1())
                .var2(source.getVar2())
                .build();
    }
}
