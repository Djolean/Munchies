package com.ingsoftware.munchies.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.Hibernate;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "delivery_info")
public class DeliveryInfo {
    @Id
    @Column(name = "delivery_id", nullable = false)
    private Integer id;

    @Column(name = "delivery_time", nullable = false)
    private LocalTime deliveryTime;

    @Column(name = "additional_charges", nullable = false, precision = 10, scale = 2)
    private BigDecimal additionalCharges;

    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    @Column(name = "last_modified_date", nullable = false)
    private Instant lastModifiedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DeliveryInfo that = (DeliveryInfo) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}