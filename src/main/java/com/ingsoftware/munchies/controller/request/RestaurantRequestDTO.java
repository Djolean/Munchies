package com.ingsoftware.munchies.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRequestDTO {
    @NotBlank(message = "Required!")
    @Size(min = 2, max = 100, message = "Minimum size is 2, maximum size is 100 characters!")
    private String restaurantName;
    @NotBlank(message = "Required!")
    @Size(min = 2, max = 100, message = "Minimum size is 2, maximum size is 100 characters!")
    private String address;
    @NotBlank(message = "Required!")
    @Pattern(regexp = "^\\+\\d+$", message = "Not a valid phone number form!")
    @Size(min = 10, max = 20, message = "Minimum size is 10, maximum size is 20 characters!")
    private String phoneNumber;
    @NotBlank(message = "Required!")
    @URL(message = "URL must start with https://")
    private String menuUrl;
    @NotBlank(message = "Required!")
    private LocalTime deliveryTime;
    @NotBlank(message = "Required!")
    @PositiveOrZero(message = "Number must be zero or positive!")
    private BigDecimal additionalCharges;

}
