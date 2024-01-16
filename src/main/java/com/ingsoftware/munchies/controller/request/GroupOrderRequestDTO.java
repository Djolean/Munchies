package com.ingsoftware.munchies.controller.request;

import lombok.*;
import jakarta.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupOrderRequestDTO {

    private String restaurantId;
    @NotEmpty(message = "Required!")
    @Size(min = 3, max = 100, message = "Minimum size is 2, maximum size is 100 characters!")
    private String creatorName;
    @Min(value = 10, message = "Timeout must be 10 minutes or greater!")
    private Integer timeout;
}
