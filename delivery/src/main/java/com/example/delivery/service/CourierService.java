package com.example.delivery.service;

import com.example.delivery.entity.Courier;
import com.example.delivery.repository.CourierRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourierService {

    private final CourierRepository courierRepository;

    @Transactional(readOnly = true)
    public List<Courier> getAvailableCouriers() {
        return courierRepository.findByBusyFalse();
    }

    @Transactional
    public void assignOrderToCourier(Courier courier, int minutes) {
        courier.setBusy(true);
        courier.setBusyUntil(LocalDateTime.now().plusMinutes(minutes));
        courierRepository.save(courier);
        log.debug("Курьер {} занят до {}", courier.getId(), courier.getBusyUntil());
    }

    @Scheduled(fixedRate = 60000) //Ну тут вопрос в том, а реально важно, что бы прям на 3 минуты с отправки, или почти 4 минуты тоже подходит.
    //Ибо иначе на каждого вызванного курьера надо будет создавать отдельный Schedule, что звучит не очень
    @Transactional
    public void releaseExpiredCouriers() {
        int released = courierRepository.releaseExpiredCouriers();
        if (released > 0) {
            log.debug("Освобождено {} курьеров", released);
        }
    }
}
