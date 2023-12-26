package com.ingsoftware.munchies.controller.response;

import lombok.Builder;
import lombok.Data;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class DeliveryInfoResponseDTO {

    private Integer deliveryId;
    private String deliveryTime;
    private BigDecimal additionalCharges;
    private Instant createdDate;
    private Instant lastModifiedDate;
    private UUID uuid;

}
