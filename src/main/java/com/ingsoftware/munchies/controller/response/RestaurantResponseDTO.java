package com.ingsoftware.munchies.controller.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalTime;

@Data
@Builder
public class RestaurantResponseDTO {
    private Integer id;
    private String restaurantName;
    private String address;
    private String phoneNumber;
    private String menuUrl;
    private LocalTime deliveryTime;
    private BigDecimal additionalCharges;
    private Instant  timeCreated;
    private Instant lastDateModified;
}