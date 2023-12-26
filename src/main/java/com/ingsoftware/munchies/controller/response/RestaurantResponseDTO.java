package com.ingsoftware.munchies.controller.response;

import com.ingsoftware.munchies.controller.response.DeliveryInfoResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
public class RestaurantResponseDTO {

    private Integer restaurantId;
    private String restaurantName;
    private String address;
    private String phoneNumber;
    private String menuUrl;
    private Instant createdDate;
    private Instant lastModifiedDate;
    private UUID uuid;
    private String shortName;
    private DeliveryInfoResponseDTO deliveryInfo;

}
