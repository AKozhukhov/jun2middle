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
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column(name="id")
    private UUID id;

    @Column(name="fio")
    private String fio;

    @Column(name="email")
    private String email;

    @Column(name="location_x")
    private int locationX;

    @Column(name="location_y")
    private int locationY;
}