package com.example.delivery.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AvailableService {
    private final Random random = new Random();

    public boolean isRecipientAvailable() {
        /**
         * Вероятность, что получатель будет на месте. Где 0 - никогда, 1 - всегда
         */
        double availabilityProbability = 0.7;
        return random.nextDouble() < availabilityProbability;
    }
}
