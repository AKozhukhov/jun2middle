package ru.itone.jun2middle.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itone.jun2middle.shop.model.entity.Product;
import ru.itone.jun2middle.shop.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * Возвращает размер товара
     *
     * @param id ID товара
     * @return размер
     */
    public int getSizeById(UUID id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()) {
            throw new RuntimeException("Продукт не найден");
        }
        return optionalProduct.get().getSize();
    }

    /**
     * Сохраняет новый продукт в БД
     *
     * @param product объект Product
     * @return созданный Product
     */
    public Product create(Product product) {
            return productRepository.save(product);
    }

    /**
     * Выводит все неоценённые товары
     *
     * @return List из неоценённых товаров
     */
    public List<Product> getUnpriced() {
        return productRepository.findByPriceIsNull();
    }

    /**
     * Устанавливает цену для товара
     *
     * @param id ID товара
     * @param price новая цена
     * @return Product с проставленной ценой
     */
    public Product setPrice(UUID id, BigDecimal price) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()) {
            throw new RuntimeException("Продукт с таким ID не существует");
        }
        else {
            Product product = optionalProduct.get();
            product.setPrice(price);
            return productRepository.save(product);
        }
    }
}