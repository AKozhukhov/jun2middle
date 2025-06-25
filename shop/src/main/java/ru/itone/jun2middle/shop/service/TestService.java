package ru.itone.jun2middle.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itone.jun2middle.shop.model.entity.Test;
import ru.itone.jun2middle.shop.repository.TestRepository;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;

    public Test create(Test test){
        return testRepository.save(test);
    }
}
