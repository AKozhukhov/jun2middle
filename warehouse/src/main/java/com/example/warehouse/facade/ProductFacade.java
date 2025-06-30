package com.example.warehouse.facade;

import com.example.warehouse.mapper.ProductMapper;
import com.example.warehouse.model.dto.CreatedProductDto;
import com.example.warehouse.model.dto.ProductDto;
import com.example.warehouse.model.entity.Product;
import com.example.warehouse.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductFacade {

    private final ProductMapper productMapper;

    private final ProductService productService;

    public List<CreatedProductDto> findAll() {
        return productService.findAll().stream()
                .map(productMapper::productToCreatedProductDto)
                .toList();
    }

    public CreatedProductDto add(ProductDto productDto) {
        Product product = productMapper.dtoProductToProduct(productDto);
        Product created = productService.add(product);
        return productMapper.productToCreatedProductDto(created);
    }

}
