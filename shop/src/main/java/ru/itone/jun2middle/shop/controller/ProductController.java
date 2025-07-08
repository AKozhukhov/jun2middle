package ru.itone.jun2middle.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itone.jun2middle.shop.facade.ProductFacade;
import ru.itone.jun2middle.shop.model.dto.product.ProductDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductFacade productFacade;

    /**
     * Добавляет новый продукт
     *
     * @param productDto объект ProductDto
     * @return созданный ProductDto
     */
    @PostMapping
    public ProductDto create(@RequestBody ProductDto productDto) {
        return productFacade.create(productDto);
    }

    /**
     * Выводит все неоценённые товары
     *
     * @return List из неоценённых товаров
     */
    @GetMapping
    public List<ProductDto> getUnpriced() {
        return productFacade.getUnpriced();
    }

    /**
     * Устанавливает цену для товара
     *
     * @param id ID товара
     * @param price новая цена
     * @return ProductDto с проставленной ценой
     */
    @PutMapping
    public ProductDto setPrice(@RequestParam UUID id, @RequestParam BigDecimal price) {
        return productFacade.setPrice(id,price);
    }
}
