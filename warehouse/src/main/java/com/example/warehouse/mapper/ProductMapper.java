package com.example.warehouse.mapper;

import com.example.warehouse.model.dto.CreatedProductDto;
import com.example.warehouse.model.dto.ProductDto;
import com.example.warehouse.model.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product dtoProductToProduct(ProductDto productDto) {
        return new Product(
                null,
                productDto.getName(),
                productDto.getSize(),
                productDto.getCount());
    }

    public CreatedProductDto productToCreatedProductDto(Product product) {
        return CreatedProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .size(product.getSize())
                .count(product.getCount())
                .build();
    }

}
