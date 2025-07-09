package com.example.delivery.entity;

import com.example.delivery.entity.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "shop_order_id", nullable = false)
    private UUID shopOrderId;

    @Column(nullable = false)
    private int size;

    @Column(name = "location_x", nullable = false)
    private int locationX;

    @Column(name = "location_y", nullable = false)
    private int locationY;

    @Column(name = "create_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime createDate;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name="status", nullable = false)
    private OrderStatus status = OrderStatus.NEW;

    private LocalDateTime arrivalTime;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;
}