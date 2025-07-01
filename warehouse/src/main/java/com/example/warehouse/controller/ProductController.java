package com.example.warehouse.controller;

import com.example.warehouse.facade.ProductFacade;
import com.example.warehouse.model.dto.CreatedProductDto;
import com.example.warehouse.model.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductFacade productFacade;

    @PostMapping
    public CreatedProductDto addProduct(@RequestBody ProductDto productDto) {
        return productFacade.add(productDto);
    }

}
