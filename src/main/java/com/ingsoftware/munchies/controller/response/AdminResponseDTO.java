package com.ingsoftware.munchies.controller.response;

import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminResponseDTO {

    private String adminId;
    private String adminName;
    private String adminEmail;
    private String password;
    private Instant createdDate;
    private Instant lastModifiedDate;
    private boolean isEnabled;

}
