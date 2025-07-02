package ru.itone.jun2middle.shop.mapper;

import org.springframework.stereotype.Service;
import ru.itone.jun2middle.shop.model.dto.product.CreatedProductDto;
import ru.itone.jun2middle.shop.model.dto.product.ProductDto;
import ru.itone.jun2middle.shop.model.entity.Product;

/**
 * Класс для преобразования Entity и DTO Product
 */
@Service
public class ProductMapper {

    /**
     * Преобразование ProductDto в Product
     *
     * @param productDto Объект ProductDto
     * @return Product (Entity)
     */
    public Product toEntity(ProductDto productDto) {
        return new Product(
                null,
                productDto.getWarehouseId(),
                productDto.getName(),
                productDto.getSize());
    }

    /**
     * Преобразование Product в CreatedProductDto
     *
     * @param product Product (Entity)
     * @return Объект CreatedProductDto
     */
    public CreatedProductDto toCreatedDto(Product product) {
        return CreatedProductDto.builder()
                .id(product.getId())
                .warehouseId(product.getWarehouseId())
                .name(product.getName())
                .size(product.getSize())
                .build();
    }
}

