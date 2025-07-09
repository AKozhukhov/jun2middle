package ru.itone.jun2middle.shop.model.entity;

import jakarta.persistence.*;
import java.util.UUID;

import lombok.*;
import ru.itone.jun2middle.shop.model.entity.enums.Status;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column(name="id")
    private UUID id;

    @Column(name="product_id")
    private UUID productId;

    @Column(name="user_id")
    private UUID userId;

    @Enumerated(EnumType.STRING)
    @Column(name="status", columnDefinition="order_status")
    private Status status;

    @Column(name="description")
    private String description;
}