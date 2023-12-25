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
@ToString
@Table(name = "item")
@RequiredArgsConstructor
public class Item {
    @Id
    @Column(name = "item_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "group_order_fk", nullable = false)
    @ToString.Exclude
    private GroupOrder groupOrderFk;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "employee_name", length = 10)
    private String employeeName;

    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    @Column(name = "last_modified_date", nullable = false)
    private Instant lastModifiedDate;
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

}