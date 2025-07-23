package com.example.warehouse.controller;

import com.example.warehouse.client.ShopClientSender;
import com.example.warehouse.model.dto.CreatedProductDto;
import com.example.warehouse.model.dto.ProductDto;
import com.example.warehouse.model.entity.Product;
import com.example.warehouse.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductIntegrationTest extends AbstractProductTest {

    public static final String PRODUCT_NAME = "NewProduct";
    public static final int PRODUCT_SIZE = 2;
    public static final int PRODUCT_COUNT = 100;

    @Autowired
    protected ProductRepository productRepository;

    @MockBean
    private ShopClientSender shopClientSender;

    @Test
    public void whenAddProduct_thenAddAndSendProduct() throws Exception {
        ProductDto productDto = ProductDto.builder()
                .name(PRODUCT_NAME)
                .size(PRODUCT_SIZE)
                .count(PRODUCT_COUNT)
                .build();

        Mockito.when(shopClientSender.sendProduct(any())).thenReturn(any());

        String actualResponse = mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        CreatedProductDto createdProductDto = objectMapper.readValue(actualResponse, CreatedProductDto.class);

        List<Product> products = productRepository.findAll();
        assertEquals(5, products.size());

        assertEquals(PRODUCT_NAME, createdProductDto.getName());
        assertEquals(PRODUCT_SIZE, createdProductDto.getSize());
        assertEquals(PRODUCT_COUNT, createdProductDto.getCount());
    }

    @Test
    public void whenRepositoryFindAll_thenReturnFour() {
        List<Product> products = productRepository.findAll();
        assertEquals(4, products.size());
    }

}
