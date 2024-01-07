package com.ingsoftware.munchies.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@ToString
@Table(name = "restaurant")
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private String restaurantId;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "delivery_fk")
    @ToString.Exclude
    private DeliveryInfo deliveryInfo;
    @Column(nullable = false)
    private String restaurantName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, length = 15)
    private String phoneNumber;

    @Column(nullable = false)
    private String menuUrl;

    @Column(nullable = false, updatable = false)
    private Instant createdDate;

    @Column(nullable = false)
    private Instant lastModifiedDate;

    @Column(nullable = false)
    private String shortName;
}