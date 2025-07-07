package ru.itone.jun2middle.shop.mapper;

import org.springframework.stereotype.Service;
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
                productDto.getId(),
                productDto.getWarehouseId(),
                productDto.getName(),
                productDto.getSize(),
                productDto.getPrice());
    }

    /**
     * Преобразование Product в ProductDto
     *
     * @param product Product (Entity)
     * @return Объект ProductDto
     */
    public ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .warehouseId(product.getWarehouseId())
                .name(product.getName())
                .size(product.getSize())
                .price(product.getPrice())
                .build();
    }
}

