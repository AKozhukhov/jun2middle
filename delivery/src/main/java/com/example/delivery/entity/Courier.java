package com.example.delivery.entity;


import com.example.delivery.entity.enums.Transport;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "couriers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Courier {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String fio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "transport_enum")
    private Transport transport;
}
