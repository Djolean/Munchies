package com.ingsoftware.munchies.controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequestDTO {

    private String itemId;

    @NotEmpty(message = "Required!")
    @Size(min = 3, max = 100, message = "Minimum size is 2, maximum size is 100 characters!")
    private String itemName;

    @NotEmpty(message = "Required!")
    @Size(min = 3, max = 100, message = "Minimum size is 2, maximum size is 100 characters!")
    private String employeeName;

    @PositiveOrZero(message = "Number must be zero or positive!")
    private Double price;
}
