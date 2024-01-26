package com.ingsoftware.munchies.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;
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

    @ManyToOne
    @JoinColumn(name = "restaurant_fk", nullable = false)
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

    @OneToMany(mappedBy = "groupOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();
}