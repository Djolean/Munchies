package com.ingsoftware.munchies.model.dto;

import lombok.*;

import java.time.Instant;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantDTO {
    private Integer id;
    private String restaurantName;
    private String address;
    private String phoneNumber;
    private String menuUrl;
    private Instant createdDate;
    private Instant lastModifiedDate;

}
