package com.ingsoftware.munchies.controller.response;

import com.ingsoftware.munchies.model.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupOrderResponseDTO {

    private String groupOrderId;

    private String creatorName;

    private String groupOrderUrl;

    private Restaurant restaurant;

    private Integer groupOrderTimeout;

    private Instant createdDate;

    private Instant lastModifiedDate;

    private Double totalPrice;
}
