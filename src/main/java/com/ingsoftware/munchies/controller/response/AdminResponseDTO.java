package com.ingsoftware.munchies.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
