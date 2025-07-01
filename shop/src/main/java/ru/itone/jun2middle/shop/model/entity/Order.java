package ru.itone.jun2middle.shop.model.entity;

import jakarta.persistence.*;
import java.util.UUID;

import lombok.*;

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
    private UUID product_id;

    @Column(name="user_id")
    private UUID user_id;
}