package com.ingsoftware.munchies.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
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
