package com.ingsoftware.munchies.controller.response;

import com.ingsoftware.munchies.model.entity.GroupOrder;
import lombok.*;

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
    private Instant createdDate;
    private Instant lastModifiedDate;

}
