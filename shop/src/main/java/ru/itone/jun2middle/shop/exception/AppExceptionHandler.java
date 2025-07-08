package ru.itone.jun2middle.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(ShopApplicationException.class)
    public ResponseEntity<String> handleShopApplicationException(ShopApplicationException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getClass().getSimpleName()+": "+exception.getMessage());
    }
}
