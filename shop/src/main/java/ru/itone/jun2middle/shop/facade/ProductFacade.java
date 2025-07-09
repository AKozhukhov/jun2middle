package ru.itone.jun2middle.shop.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itone.jun2middle.shop.mapper.ProductMapper;
import ru.itone.jun2middle.shop.model.dto.product.ProductDto;
import ru.itone.jun2middle.shop.model.entity.Product;
import ru.itone.jun2middle.shop.service.ProductService;

@Service
@RequiredArgsConstructor
public class ProductFacade {
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductDto create(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        Product createdProduct = productService.create(product);
        return productMapper.toDto(createdProduct);
    }
}
