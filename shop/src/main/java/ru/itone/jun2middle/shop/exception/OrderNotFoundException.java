package ru.itone.jun2middle.shop.exception;

public class OrderNotFoundException extends ShopApplicationException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
