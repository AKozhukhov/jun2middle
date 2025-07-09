package ru.itone.jun2middle.shop.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itone.jun2middle.shop.mapper.ProductMapper;
import ru.itone.jun2middle.shop.model.dto.product.ProductDto;
import ru.itone.jun2middle.shop.model.entity.Product;
import ru.itone.jun2middle.shop.service.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductFacade {
    private final ProductService productService;
    private final ProductMapper productMapper;

    /**
     * Добавляет новый продукт
     *
     * @param productDto объект ProductDto
     * @return созданный ProductDto
     */
    public ProductDto create(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        Product createdProduct = productService.create(product);
        return productMapper.toDto(createdProduct);
    }

    /**
     * Выводит все неоценённые товары
     *
     * @return List из неоценённых товаров
     */
    public List<ProductDto> getUnpriced() {
        return productService.getUnpriced().stream()
                .map(productMapper::toDto)
                .toList();
    }

    /**
     * Устанавливает цену для товара
     *
     * @param id ID товара
     * @param price новая цена
     * @return ProductDto с проставленной ценой
     */
    public ProductDto setPrice(UUID id, BigDecimal price) {
        return productMapper.toDto(productService.setPrice(id,price));
    }
}
