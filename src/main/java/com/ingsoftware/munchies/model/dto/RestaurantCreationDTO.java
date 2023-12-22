package com.ingsoftware.munchies.model.dto;

import com.ingsoftware.munchies.model.entity.DeliveryInfo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantCreationDTO {
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

    private DeliveryInfoCreationDTO deliveryInfoCreationDTO;

//    @FutureOrPresent(message = "Date must be present or future!")
//    private Instant createdDate;
//    @FutureOrPresent(message = "Date must be present or future!")
//    private Instant lastModifiedDate;
}
