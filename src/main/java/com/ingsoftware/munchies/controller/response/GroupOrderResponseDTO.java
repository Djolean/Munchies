package com.ingsoftware.munchies.controller.response;

import com.ingsoftware.munchies.model.entity.Restaurant;
import lombok.*;

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
    private Integer timeout;
    private Instant dateCreated;
    private Instant lastTimeModified;
    private Double totalPrice;
}
