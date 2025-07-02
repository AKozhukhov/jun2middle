package com.example.warehouse.service;

import com.example.warehouse.model.entity.Product;
import com.example.warehouse.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(UUID productId) {
        return productRepository.findById(productId);
    }

    @Transactional
    public Product add(Product product){
        Optional<Product> optionalProduct = productRepository.findByName(product.getName());
        if (optionalProduct.isPresent()) {
            Product findProduct = optionalProduct.get();
            findProduct.setCount(product.getCount() + findProduct.getCount());
            return productRepository.save(findProduct);
        }
        return productRepository.save(product);
    }

}
