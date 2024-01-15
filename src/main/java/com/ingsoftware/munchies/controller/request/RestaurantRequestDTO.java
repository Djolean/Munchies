package com.ingsoftware.munchies.controller.request;

import lombok.*;
import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRequestDTO {

    private String restaurantId;
    @NotEmpty(message = "Required!")
    @Size(min = 2, max = 100, message = "Minimum size is 2, maximum size is 100 characters!")
    private String restaurantName;
    @NotEmpty(message = "Required!")
    @Size(min = 2, max = 100, message = "Minimum size is 2, maximum size is 100 characters!")
    private String address;
    @NotEmpty(message = "Required!")
    @Pattern(regexp = "^\\+\\d+$", message = "Not a valid phone number form!")
    @Size(min = 10, max = 20, message = "Minimum size is 10, maximum size is 20 characters!")
    private String phoneNumber;
    @NotEmpty(message = "Required!")
    @URL(message = "URL must start with https://")
    private String menuUrl;
    @PositiveOrZero(message = "Number must be zero or positive!")
    @NotEmpty(message = "Required!")
    private String deliveryTime;
    @PositiveOrZero(message = "Number must be zero or positsive!")
    private BigDecimal additionalCharges;

}
