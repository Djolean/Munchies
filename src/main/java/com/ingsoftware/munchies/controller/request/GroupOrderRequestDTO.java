package com.ingsoftware.munchies.controller.request;

import com.ingsoftware.munchies.model.entity.Restaurant;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupOrderRequestDTO {
    @NotEmpty(message = "Required!")
    @Size(min = 3, max = 100, message = "Minimum size is 2, maximum size is 100 characters!")
    private String creatorName;
    @NotEmpty(message = "Required!")
    @Size(min = 3, max = 100, message = "Minimum size is 2, maximum size is 100 characters!")
    private Restaurant restaurant;
    @NotEmpty(message = "Required!")
    @Min(value = 10, message = "Timeout must be 10 minutes or greater!")
    private Integer timeout;

}
