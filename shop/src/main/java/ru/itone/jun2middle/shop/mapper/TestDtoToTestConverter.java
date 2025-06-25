package ru.itone.jun2middle.shop.mapper;

import org.springframework.stereotype.Service;
import ru.itone.jun2middle.shop.model.dto.test.TestDto;
import ru.itone.jun2middle.shop.model.entity.Test;
import org.springframework.core.convert.converter.*;

@Service
public class TestDtoToTestConverter implements Converter<TestDto, Test> {

    @Override
    public Test convert(TestDto source) {
        Test test = new Test();
        test.setVar1(source.getVar1());
        test.setVar2(source.getVar2());
        return test;
    }
}
