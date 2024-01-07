package com.ingsoftware.munchies.controller.response;

import lombok.Builder;
import lombok.*;


import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Builder
public class DeliveryInfoResponseDTO {

    private String deliveryId;
    private String deliveryTime;
    private BigDecimal additionalCharges;
    private Instant createdDate;
    private Instant lastModifiedDate;

}
