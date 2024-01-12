package com.ingsoftware.munchies.controller.response;

import lombok.Builder;
import lombok.*;
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
