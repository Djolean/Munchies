package com.ingsoftware.munchies.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "restaurant")
@RequiredArgsConstructor
public class Restaurant {
    @Id
    @Column(nullable = false)
    private Integer restaurantId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
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

    @Column(nullable = false)
    private Instant createdDate;

    @Column(nullable = false)
    private Instant lastModifiedDate;
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(nullable = false)
    private String shortName;

//    public Restaurant() {
//        this.createdDate = Instant.from(LocalDateTime.now());
//    }
}