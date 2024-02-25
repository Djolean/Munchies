package com.ingsoftware.munchies.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@Table(name = "item")
@RequiredArgsConstructor
@AllArgsConstructor

public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private String itemId;

    @ManyToOne
    @JoinColumn(name = "group_order_fk", nullable = false)
    private GroupOrder groupOrder;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "employee_name", length = 10)
    private String employeeName;

    @Column(name = "created_date", nullable = false, updatable = false)
    private Instant createdDate;

    @Column(name = "last_modified_date", nullable = false)
    private Instant lastModifiedDate;

    @Column(name = "price", nullable = false)
    private Double price;

}