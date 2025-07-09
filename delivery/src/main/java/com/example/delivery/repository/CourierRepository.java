package com.example.delivery.repository;

import com.example.delivery.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourierRepository extends JpaRepository<Courier, UUID> {

    @Transactional(readOnly = true)
    List<Courier> findByBusyFalse();

    @Modifying
    @Transactional
    @Query("UPDATE Courier c SET c.busy = false, c.busyUntil = null " +
            "WHERE c.busy = true AND c.busyUntil <= CURRENT_TIMESTAMP")
    int releaseExpiredCouriers();
}
