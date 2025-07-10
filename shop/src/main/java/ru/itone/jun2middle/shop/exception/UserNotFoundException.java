package ru.itone.jun2middle.shop.exception;

public class UserNotFoundException extends ShopApplicationException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
