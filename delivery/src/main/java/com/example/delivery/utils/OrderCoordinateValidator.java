package com.example.delivery.utils;

import com.example.delivery.exception.InvalidCoordinatesException;
import org.springframework.stereotype.Component;

@Component
public class OrderCoordinateValidator {

    public int validateAndCalculateDistance(int x, int y) {
        if (x < 0 || x > 9 || y < 0 || y > 9) {
            throw new InvalidCoordinatesException("Координаты не находятся в зоне доставки (0-9)");
        }
        return Math.abs(x) + Math.abs(y);
    }
}
