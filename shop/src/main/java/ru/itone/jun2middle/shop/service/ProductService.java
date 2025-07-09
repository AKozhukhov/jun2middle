package ru.itone.jun2middle.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itone.jun2middle.shop.model.entity.Product;
import ru.itone.jun2middle.shop.repository.ProductRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public int getSizeById(UUID id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()) {
            throw new RuntimeException("Продукт не найден");
        }
        return optionalProduct.get().getSize();
    }

    public Product create(Product product) {
            return productRepository.save(product);
    }
}