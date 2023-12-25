package com.ingsoftware.munchies.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "delivery_info")
public class DeliveryInfo {
    @Id
    @Column(nullable = false)
    private Integer deliveryId;
    @Column(nullable = false)
    private LocalTime deliveryTime;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal additionalCharges;

    @Column(nullable = false)
    private Instant createdDate;

    @Column(nullable = false)
    private Instant lastModifiedDate;
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
}