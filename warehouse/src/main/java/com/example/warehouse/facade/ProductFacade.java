package com.example.warehouse.facade;

import com.example.warehouse.client.ShopClient;
import com.example.warehouse.exception.ProductException;
import com.example.warehouse.mapper.ProductMapper;
import com.example.warehouse.model.dto.CreatedProductDto;
import com.example.warehouse.model.dto.ProductDto;
import com.example.warehouse.model.entity.Product;
import com.example.warehouse.service.FillingService;
import com.example.warehouse.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductFacade {

    private final ProductMapper productMapper;

    private final ProductService productService;

    private final FillingService fillingService;

    private final ShopClient shopClient;

    public List<CreatedProductDto> findAll() {
        return productService.findAll().stream()
                .map(productMapper::productToCreatedProductDto)
                .toList();
    }

    @Transactional
    public CreatedProductDto add(ProductDto productDto) {
        long freeSpace = fillingService.freeSpaceInWarehouse();
        if (freeSpace < productDto.getCount()) {
            throw new ProductException("No free space in warehouse");
        }
        Product product = productService.add(productMapper.dtoProductToProduct(productDto));
        CreatedProductDto createdProductDto = productMapper.productToCreatedProductDto(product);
        shopClient.sendProduct(createdProductDto);
        return createdProductDto;
    }

}
