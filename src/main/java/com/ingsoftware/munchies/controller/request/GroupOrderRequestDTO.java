package com.ingsoftware.munchies.controller.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupOrderRequestDTO {

    private String groupOrderId;

    @NotEmpty(message = "Required!")
    @Size(min = 3, max = 100, message = "Minimum size is 2, maximum size is 100 characters!")
    private String creatorName;

    @Min(value = 10, message = "Timeout must be 10 minutes or greater!")
    private Integer groupOrderTimeout;

}
