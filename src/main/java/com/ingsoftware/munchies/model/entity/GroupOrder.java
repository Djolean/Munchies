package com.ingsoftware.munchies.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@Table(name = "group_order")
@RequiredArgsConstructor
@AllArgsConstructor
public class GroupOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private String groupOrderId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_fk", nullable = false)
    @ToString.Exclude
    private Restaurant restaurant;
    @Column(nullable = false)
    private String creatorName;

    @Column(nullable = false)
    private String groupOrderUrl;

    @Column(nullable = false)
    private Integer groupOrderTimeout;

    @Column(nullable = false)
    private Instant createdDate;

    @Column(nullable = false)
    private Instant lastModifiedDate;

    private Double totalPrice;
}