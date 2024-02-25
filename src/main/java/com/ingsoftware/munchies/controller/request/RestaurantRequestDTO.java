package com.ingsoftware.munchies.controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

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
