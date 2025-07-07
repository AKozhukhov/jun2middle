package ru.itone.jun2middle.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itone.jun2middle.shop.facade.ProductFacade;
import ru.itone.jun2middle.shop.model.dto.product.ProductDto;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductFacade productFacade;

    @PostMapping
    public ProductDto create(@RequestBody ProductDto productDto) {
        return productFacade.create(productDto);
    }
}
