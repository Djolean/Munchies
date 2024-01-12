package com.ingsoftware.munchies.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "group_order")
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class GroupOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private String groupOrderId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_fk", nullable = false)
    @ToString.Exclude
    private Restaurant restaurant;
    @Column
    private String creatorName;

    @Column(nullable = false)
    private String groupOrderUrl;

    @Column(nullable = false)
    private Integer timeout;

    @Column(nullable = false, updatable = false)
    private Instant dateCreated;

    @Column(nullable = false)
    private Instant lastModifiedDate;

    private Double totalPrice;
}