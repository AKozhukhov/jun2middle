package com.example.warehouse.client;

import com.example.warehouse.exception.ProductException;
import com.example.warehouse.mapper.ProductMapper;
import com.example.warehouse.model.dto.CreatedProductDto;
import com.example.warehouse.model.dto.ShopProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class ShopClientSender {

    private final ProductMapper productMapper;

    private final RestClient shopRestClient;

    public CreatedProductDto sendProduct(CreatedProductDto createdProductDto) {
        try {
            shopRestClient.post()
                    .uri("/api/v1/products")
                    .body(productMapper.createdProductDtoToShopProductRequest(createdProductDto))
                    .retrieve()
                    .toEntity(ShopProductRequest.class);
        } catch (HttpServerErrorException | HttpClientErrorException e){
            throw new ProductException("Bad request to Shop. Product name = %s".formatted(createdProductDto.getName()));
        }
        return createdProductDto;
    }

}
