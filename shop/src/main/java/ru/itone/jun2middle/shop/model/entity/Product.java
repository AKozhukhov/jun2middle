package ru.itone.jun2middle.shop.model.entity;

import jakarta.persistence.*;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column(name="id")
    private UUID id;

    @Column(name="warehouse_id")
    private UUID warehouseId;

    @Column(name="name")
    private String name;

    @Column(name="size")
    private int size;

    @Column(name="price")
    private double price;
}