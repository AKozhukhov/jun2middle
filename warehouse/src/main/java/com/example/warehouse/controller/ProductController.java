package com.example.warehouse.controller;

import com.example.warehouse.facade.ProductFacade;
import com.example.warehouse.model.dto.CreatedProductDto;
import com.example.warehouse.model.dto.ProductDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductFacade productFacade;

    /**
     * Список всех продуктоа на складе
     * @return List<CreatedProductDto>
     */
    @GetMapping
    public List<CreatedProductDto> findAllProducts() {
        return productFacade.findAll();
    }

    /**
     * Создание продукта (сохранение в БД)
     * Если товар с таким названием уже есть на складе -
     * увеличить его количество
     * @param productDto с полями (name, size, count)
     * @return CreatedProductDto
     */
    @PostMapping
    public CreatedProductDto addProduct(@Valid @RequestBody ProductDto productDto) {
        return productFacade.add(productDto);
    }

}
