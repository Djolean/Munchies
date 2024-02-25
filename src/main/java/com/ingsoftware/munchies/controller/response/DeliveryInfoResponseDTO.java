package com.ingsoftware.munchies.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryInfoResponseDTO {

    private String deliveryId;

    private String deliveryTime;

    private BigDecimal additionalCharges;

    private Instant createdDate;

    private Instant lastModifiedDate;
}
