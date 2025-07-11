package com.example.warehouse.client;

import com.example.warehouse.exception.ProductException;
import com.example.warehouse.model.dto.CreatedProductDto;
import com.example.warehouse.model.dto.ShopProductRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;

@Service
public class ShopClient {

    @Value("${app.integration.shop-base-url}")
    private String shopBaseUrl;

    public void sendProduct(CreatedProductDto createdProductDto) {
        RestClient restClient = RestClient.create(shopBaseUrl);
        ShopProductRequest shopProductRequest = ShopProductRequest.builder()
                .warehouseId(createdProductDto.getId())
                .name(createdProductDto.getName())
                .size(createdProductDto.getSize())
                .build();
        try {
            restClient.post()
                    .uri("/api/v1/products")
                    .body(shopProductRequest)
                    .retrieve()
                    .toEntity(ShopProductRequest.class);
        } catch (HttpServerErrorException | HttpClientErrorException e){
            throw new ProductException("Bad request to Shop. Product name = %s".formatted(createdProductDto.getName()));
        }
    }

}
