package com.ingsoftware.munchies.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.Instant;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Item item = (Item) o;
        return getId() != null && Objects.equals(getId(), item.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}