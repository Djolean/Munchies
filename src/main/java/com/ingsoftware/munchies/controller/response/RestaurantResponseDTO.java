package com.ingsoftware.munchies.controller.response;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantResponseDTO{

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
