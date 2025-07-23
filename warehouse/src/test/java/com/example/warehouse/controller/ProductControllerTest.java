package com.example.warehouse.controller;

import com.example.warehouse.facade.ProductFacade;
import com.example.warehouse.mapper.ProductMapper;
import com.example.warehouse.model.dto.CreatedProductDto;
import com.example.warehouse.model.dto.ProductDto;
import com.example.warehouse.model.entity.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private ProductMapper productMapper;

    @MockBean
    private ProductFacade productFacade;

    @Test
    public void whenFindAll_thenReturnAllClients() throws Exception {

        Mockito.when(productFacade.findAll()).thenReturn(List.of());

        String actualResponse = mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println("UUUUUUUUUUUU " + actualResponse);

        verify(productFacade, times(1)).findAll();
    }

    @Test
    public void whenCreateClient_thenReturnNewClient() throws Exception {
        ProductDto productDto = ProductDto.builder()
                .name("PRODUCT_NAME")
                .size(3)
                .count(78)
                .build();
        Product product = productMapper.dtoProductToProduct(productDto);
        product.setId(UUID.randomUUID());
        CreatedProductDto createdProductDto = productMapper.productToCreatedProductDto(product);

        Mockito.when(productFacade.add(productDto)).thenReturn(createdProductDto);

        String actualResponse = mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println("UUUUUUUUUUUU " + actualResponse);

        verify(productFacade, times(1)).add(productDto);
    }

}
