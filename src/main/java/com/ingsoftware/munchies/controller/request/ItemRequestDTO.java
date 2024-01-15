package com.ingsoftware.munchies.controller.request;

import jakarta.validation.constraints.*;
import lombok.*;



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
}
