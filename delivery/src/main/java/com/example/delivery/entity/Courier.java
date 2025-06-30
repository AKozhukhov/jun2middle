package com.example.delivery.entity;


import com.example.delivery.entity.enums.Transport;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "couriers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Courier {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false)
    private String fio;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(nullable = false)
    private Transport transport;

    @Column(nullable = false)
    private boolean busy = false;

    @Column(name = "busy_until")
    private LocalDateTime busyUntil;
}
