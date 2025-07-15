package ru.itone.jun2middle.shop.facade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.itone.jun2middle.shop.mapper.ProductMapper;
import ru.itone.jun2middle.shop.model.dto.product.ProductDto;
import ru.itone.jun2middle.shop.model.entity.Product;
import ru.itone.jun2middle.shop.service.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductFacadeTest {

    @Mock
    private ProductService productService;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductFacade productFacade;

    @Test
    void create_returnsCorrectResponse() {

        UUID warehouseId = UUID.randomUUID();
        BigDecimal price = BigDecimal.valueOf(30.00);
        ProductDto productDto = ProductDto.builder()
                .id(null)
                .warehouseId(warehouseId)
                .name("хлеб")
                .size(1)
                .price(price)
                .build();

        UUID generatedId = UUID.randomUUID();
        ProductDto expectedReturnDto = ProductDto.builder()
                .id(generatedId)
                .warehouseId(warehouseId)
                .name("хлеб")
                .size(1)
                .price(price)
                .build();

        Product product = new Product(
                null,
                warehouseId,
                "хлеб",
                1,
                price);

        Product cretedProduct = new Product(
                generatedId,
                warehouseId,
                "хлеб",
                1,
                price);

        when(productMapper.toEntity(productDto)).thenReturn(product);
        when(productService.create(product)).thenReturn(cretedProduct);
        when(productMapper.toDto(cretedProduct)).thenReturn(expectedReturnDto);

        ProductDto actualReturnDto = productFacade.create(productDto);

        Assertions.assertNotNull(actualReturnDto);
        Assertions.assertEquals(generatedId,actualReturnDto.getId());
        Assertions.assertEquals(warehouseId,actualReturnDto.getWarehouseId());
        Assertions.assertEquals("хлеб",actualReturnDto.getName());
        Assertions.assertEquals(1,actualReturnDto.getSize());
        Assertions.assertEquals(price,actualReturnDto.getPrice());
    }

    @Test
    void getUnpriced_returnsCorrectResponse() {

        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID warehouseId = UUID.randomUUID();

        Product entity1 = new Product(
                id1,
                warehouseId,
                "хлеб",
                1,
                null);
        Product entity2 = new Product(
                id2,
                warehouseId,
                "колбаса",
                1,
                null);
        List<Product> entityList = new ArrayList<>();
        entityList.add(entity1);
        entityList.add(entity2);

        ProductDto dto1 = ProductDto.builder()
                .id(id1)
                .warehouseId(warehouseId)
                .name("хлеб")
                .size(1)
                .price(null)
                .build();
        ProductDto dto2 = ProductDto.builder()
                .id(id2)
                .warehouseId(warehouseId)
                .name("колбаса")
                .size(1)
                .price(null)
                .build();
        List<ProductDto> expectedDtoList = new ArrayList<>();
        expectedDtoList.add(dto1);
        expectedDtoList.add(dto2);

        when(productService.getUnpriced()).thenReturn(entityList);
        when(productMapper.toDto(entity1)).thenReturn(dto1);
        when(productMapper.toDto(entity2)).thenReturn(dto2);

        List<ProductDto> actualDtoList = productFacade.getUnpriced();

        Assertions.assertNotNull(actualDtoList);
        Assertions.assertEquals(2,actualDtoList.size());

        ProductDto actualDto1 = actualDtoList.get(0);

        Assertions.assertNotNull(actualDto1);
        Assertions.assertEquals(id1,actualDto1.getId());
        Assertions.assertEquals(warehouseId,actualDto1.getWarehouseId());
        Assertions.assertEquals("хлеб",actualDto1.getName());
        Assertions.assertEquals(1,actualDto1.getSize());
        Assertions.assertNull(actualDto1.getPrice());

        ProductDto actualDto2 = actualDtoList.get(1);

        Assertions.assertNotNull(actualDto2);
        Assertions.assertEquals(id2,actualDto2.getId());
        Assertions.assertEquals(warehouseId,actualDto2.getWarehouseId());
        Assertions.assertEquals("колбаса",actualDto2.getName());
        Assertions.assertEquals(1,actualDto2.getSize());
        Assertions.assertNull(actualDto2.getPrice());
    }

    @Test
    void setPrice_returnsCorrectResponse() {
        UUID id = UUID.randomUUID();
        UUID warehouseId = UUID.randomUUID();
        BigDecimal price = BigDecimal.valueOf(100.00);

        Product product = new Product(
                null,
                warehouseId,
                "хлеб",
                1,
                price);

        ProductDto expectedProductDto = ProductDto.builder()
                .id(id)
                .warehouseId(warehouseId)
                .name("хлеб")
                .size(1)
                .price(price)
                .build();

        when(productService.setPrice(id,price)).thenReturn(product);
        when(productMapper.toDto(product)).thenReturn(expectedProductDto);

        ProductDto actualProductDto = productFacade.setPrice(id,price);

        Assertions.assertNotNull(actualProductDto);
        Assertions.assertEquals(id,actualProductDto.getId());
        Assertions.assertEquals(warehouseId,actualProductDto.getWarehouseId());
        Assertions.assertEquals("хлеб",actualProductDto.getName());
        Assertions.assertEquals(1,actualProductDto.getSize());
        Assertions.assertEquals(price,actualProductDto.getPrice());
    }
}
