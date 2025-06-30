package com.example.delivery.entity.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Transport {
    FOOT(1),
    MOTORCYCLE(2),
    CAR(4);

    private final int capacity;
}
