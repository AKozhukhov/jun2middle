package ru.itone.jun2middle.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itone.jun2middle.shop.facade.TestFacade;
import ru.itone.jun2middle.shop.model.dto.test.CreatedTestDto;
import ru.itone.jun2middle.shop.model.dto.test.TestDto;

@RestController
@RequestMapping("/api/v1/tests")
@RequiredArgsConstructor
public class TestController {

    private final TestFacade testFacade;

    /**
     *
     * @param dto
     */
    @PostMapping
    public CreatedTestDto create(@RequestBody TestDto dto) {
        System.out.println("*******");
        return testFacade.create(dto);
    }
}
