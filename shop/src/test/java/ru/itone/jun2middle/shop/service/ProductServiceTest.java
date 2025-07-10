package ru.itone.jun2middle.shop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.itone.jun2middle.shop.model.entity.Product;
import ru.itone.jun2middle.shop.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void getSizeById_returnsCorrectSize() {
        UUID id = UUID.randomUUID();
        UUID warehouseId = UUID.randomUUID();
        int expectedSize = 1;

        Optional<Product> optionalProduct = Optional.of(new Product(
                id,
                warehouseId,
                "хлеб",
                1,
                BigDecimal.valueOf(30.00)));

        when(productRepository.findById(id)).thenReturn(optionalProduct);

        int actualPrice = productService.getSizeById(id);

        Assertions.assertEquals(expectedSize,actualPrice);
    }

    @Test
    void create_saveProductSuccessfully() {
        UUID warehouseId = UUID.randomUUID();
        BigDecimal price = BigDecimal.valueOf(30.00);

        Product product = new Product(
                null,
                warehouseId,
                "хлеб",
                1,
                price);

        UUID generatedId = UUID.randomUUID();
        Product cretedProduct = new Product(
                generatedId,
                warehouseId,
                "хлеб",
                1,
                price);

        when(productRepository.save(product)).thenReturn(cretedProduct);

        Product actualProduct = productService.create(product);

        Assertions.assertNotNull(actualProduct);
        Assertions.assertEquals(generatedId,actualProduct.getId());
        Assertions.assertEquals(warehouseId,actualProduct.getWarehouseId());
        Assertions.assertEquals("хлеб",actualProduct.getName());
        Assertions.assertEquals(1,actualProduct.getSize());
        Assertions.assertEquals(price,actualProduct.getPrice());
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

        when(productRepository.findByPriceIsNull()).thenReturn(entityList);

        List<Product> actualEntityList = productService.getUnpriced();

        Assertions.assertNotNull(actualEntityList);
        Assertions.assertEquals(2,actualEntityList.size());

        Product actualEntity1 = actualEntityList.get(0);

        Assertions.assertNotNull(actualEntity1);
        Assertions.assertEquals(id1,actualEntity1.getId());
        Assertions.assertEquals(warehouseId,actualEntity1.getWarehouseId());
        Assertions.assertEquals("хлеб",actualEntity1.getName());
        Assertions.assertEquals(1,actualEntity1.getSize());
        Assertions.assertNull(actualEntity1.getPrice());

        Product actualEntity2 = actualEntityList.get(1);

        Assertions.assertNotNull(actualEntity2);
        Assertions.assertEquals(id2,actualEntity2.getId());
        Assertions.assertEquals(warehouseId,actualEntity2.getWarehouseId());
        Assertions.assertEquals("колбаса",actualEntity2.getName());
        Assertions.assertEquals(1,actualEntity2.getSize());
        Assertions.assertNull(actualEntity2.getPrice());
    }

    @Test
    void setPrice_setsPriceSuccessfully() {
        UUID id = UUID.randomUUID();
        UUID warehouseId = UUID.randomUUID();
        BigDecimal price = BigDecimal.valueOf(30.00);

        Product unpricedProduct = new Product(
                id,
                warehouseId,
                "хлеб",
                1,
                null);

        Product pricedProduct = new Product(
                id,
                warehouseId,
                "хлеб",
                1,
                price);

        when(productRepository.findById(id)).thenReturn(Optional.of(unpricedProduct));
        when(productRepository.save(any(Product.class))).then(AdditionalAnswers.returnsFirstArg());

        Product actualProduct = productService.setPrice(id,price);

        Assertions.assertNotNull(actualProduct);
        Assertions.assertNotNull(actualProduct.getPrice());
        Assertions.assertEquals(id,actualProduct.getId());
        Assertions.assertEquals(warehouseId,actualProduct.getWarehouseId());
        Assertions.assertEquals("хлеб",actualProduct.getName());
        Assertions.assertEquals(1,actualProduct.getSize());
        Assertions.assertEquals(price,actualProduct.getPrice());
    }
}
