package ru.itone.jun2middle.shop.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import ru.itone.jun2middle.shop.model.dto.test.CreatedTestDto;
import ru.itone.jun2middle.shop.model.dto.test.TestDto;
import ru.itone.jun2middle.shop.model.entity.Test;
import ru.itone.jun2middle.shop.service.TestService;

@Service
@RequiredArgsConstructor
public class TestFacade {
    private final ConversionService conversionService;
    private final TestService testService;

    /**
     * Создает тестовую сущность в БД
     *
     * @param dto параметры тестовой сущности
     * @return сохраненная сущность
     */
    public CreatedTestDto create(TestDto dto) {
        Test test = conversionService.convert(dto, Test.class);
        Test created = testService.create(test);
        return conversionService.convert(created, CreatedTestDto.class);
    }
}
