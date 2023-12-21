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
@Table(name = "group_order")
@RequiredArgsConstructor
public class GroupOrder {
    @Id
    @Column(name = "group_order_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_fk", nullable = false)
    @ToString.Exclude
    private Restaurant restaurantFk;

    @Column(name = "group_order_url", nullable = false)
    private String groupOrderUrl;

    @Column(name = "group_order_timeout", nullable = false)
    private Integer groupOrderTimeout;

    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    @Column(name = "last_modified_date", nullable = false)
    private Instant lastModifiedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GroupOrder that = (GroupOrder) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}