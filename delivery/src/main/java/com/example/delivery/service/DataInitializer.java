package com.example.delivery.service;

import com.example.delivery.entity.Courier;
import com.example.delivery.entity.enums.Transport;
import com.example.delivery.repository.CourierRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Profile("!prod")
@Slf4j
public class DataInitializer {

    private final CourierRepository courierRepository;

    @PostConstruct
    public void init() {
        if (courierRepository.count() == 0) {
            List<Courier> couriers = Arrays.asList(
                    createCourier("Иванов Иван Иванович", Transport.FOOT),
                    createCourier("Петров Петр Петрович", Transport.FOOT),
                    createCourier("Сидоров Сидор Сидорович", Transport.MOTORCYCLE),
                    createCourier("Кузнецов Кузьма Кузьмич", Transport.MOTORCYCLE),
                    createCourier("Николаев Николай Николаевич", Transport.CAR),
                    createCourier("Алексеев Алексей Алексеевич", Transport.CAR)
            );
            courierRepository.saveAll(couriers);
            log.info("Тестовые курьеры добавлены");
        }
    }

    private Courier createCourier(String fio, Transport transport) {
        return Courier.builder()
                .fio(fio)
                .transport(transport)
                .busy(false)
                .busyUntil(null)
                .build();
    }
}
