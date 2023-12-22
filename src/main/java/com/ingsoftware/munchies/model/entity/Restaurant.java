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
@Table(name = "restaurant")
@RequiredArgsConstructor
public class Restaurant {
    @Id
    @Column(name = "restaurant_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "delivery_fk", nullable = false)
    @ToString.Exclude
    private DeliveryInfo deliveryFk;

    @Column(name = "restaurant_name", nullable = false)
    private String restaurantName;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;

    @Column(name = "menu_url", nullable = false)
    private String menuUrl;

    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    @Column(name = "last_modified_date", nullable = false)
    private Instant lastModifiedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Restaurant that = (Restaurant) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}