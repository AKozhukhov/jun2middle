package com.example.warehouse.service;

import com.example.warehouse.model.entity.Product;
import com.example.warehouse.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product add(Product product){
        Product findProduct = productRepository.findByName(product.getName()).orElse(null);
        if (findProduct != null) {
            product.setId(findProduct.getId());
            product.setCount(product.getCount() + findProduct.getCount());
        }
        return productRepository.save(product);
    }

}
