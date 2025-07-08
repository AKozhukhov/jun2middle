package ru.itone.jun2middle.shop.exception;

public class ProductNotFoundException extends ShopApplicationException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
