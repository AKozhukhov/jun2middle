package com.example.warehouse.service;

import com.example.warehouse.model.dto.CreatedProductDto;
import com.example.warehouse.model.entity.Product;
import com.example.warehouse.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
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
