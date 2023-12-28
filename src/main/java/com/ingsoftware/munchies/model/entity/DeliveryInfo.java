package com.ingsoftware.munchies.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@ToString
@RequiredArgsConstructor
@Table(name = "delivery_info")
@AllArgsConstructor
public class DeliveryInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private String deliveryId;
    @Column(nullable = false)
    private String deliveryTime;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal additionalCharges;

    @Column(nullable = false, updatable = false)
    private Instant createdDate;

    @Column(nullable = false)
    private Instant lastModifiedDate;
}