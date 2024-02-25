package com.ingsoftware.munchies.controller.response;

import com.ingsoftware.munchies.model.GroupOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponseDTO {

    private String itemId;

    private GroupOrder groupOrder;

    private String itemName;

    private String employeeName;

    private Double price;

    private Instant createdDate;

    private Instant lastModifiedDate;
}
