package com.ingsoftware.munchies.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@Table(name = "delivery_info")
@AllArgsConstructor
@RequiredArgsConstructor
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