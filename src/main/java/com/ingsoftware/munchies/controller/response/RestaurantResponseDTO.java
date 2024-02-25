package com.ingsoftware.munchies.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantResponseDTO {

    private String restaurantId;

    private String restaurantName;

    private String address;

    private String phoneNumber;

    private String menuUrl;

    private Instant createdDate;

    private Instant lastModifiedDate;

    private String shortName;

    private DeliveryInfoResponseDTO deliveryInfo;
}
