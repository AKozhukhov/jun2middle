package com.example.delivery.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AvailableService {
    private final Random random = new Random();

    /**
     * Вероятность, что получатель будет на месте. Где 0 - никогда, 1 - всегда
     */
    private final double availabilityProbability=0.7;

    public boolean isRecipientAvailable() {
        return random.nextDouble() < availabilityProbability;
    }
}
