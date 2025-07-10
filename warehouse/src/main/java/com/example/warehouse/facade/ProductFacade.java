package com.example.warehouse.facade;

import com.example.warehouse.exception.ProductException;
import com.example.warehouse.mapper.ProductMapper;
import com.example.warehouse.model.dto.CreatedProductDto;
import com.example.warehouse.model.dto.ProductDto;
import com.example.warehouse.model.dto.ShopProductRequest;
import com.example.warehouse.model.entity.Product;
import com.example.warehouse.service.FillingService;
import com.example.warehouse.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductFacade {

    @Value("${app.integration.shop-base-url}")
    private String shopBaseUrl;

    @Value("${app.integration.shop-product-urn}")
    private String shopProductUrn;

    private final ProductMapper productMapper;

    private final ProductService productService;

    private final FillingService fillingService;

    public List<CreatedProductDto> findAll() {
        return productService.findAll().stream()
                .map(productMapper::productToCreatedProductDto)
                .toList();
    }

    @Transactional
    public CreatedProductDto add(ProductDto productDto) {
        long freeSpace = fillingService.freeSpaceInWarehouse();
        if (freeSpace < productDto.getCount()) {
            throw new ProductException("No free space in warehouse");
        }
        Product product = productService.add(productMapper.dtoProductToProduct(productDto));
        CreatedProductDto createdProductDto = productMapper.productToCreatedProductDto(product);
        sendProductToShop(createdProductDto);
        return createdProductDto;
    }

    private void sendProductToShop(CreatedProductDto createdProductDto) {
        RestClient restClient = RestClient.create();
        ShopProductRequest shopProductRequest = ShopProductRequest.builder()
                .warehouseId(createdProductDto.getId())
                .name(createdProductDto.getName())
                .size(createdProductDto.getSize())
                .build();
        ResponseEntity<Void> response = restClient.post()
                .uri(shopBaseUrl + shopProductUrn)
                .body(shopProductRequest)
                .retrieve()
                .toBodilessEntity();
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new ProductException("Bad request to Shop. Product name = %s".formatted(createdProductDto.getName()));
        }
    }

}
