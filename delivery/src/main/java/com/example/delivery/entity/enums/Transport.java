package com.example.delivery.entity.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Transport {
    FOOT(1,1),
    MOTORCYCLE(2,3),
    CAR(4,4);

    private final int capacity;
    private final int speed;
}
