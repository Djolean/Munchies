package com.ingsoftware.munchies.controller.request;

import jakarta.validation.constraints.Email;
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
public class AdminRequestDTO {

    private String adminId;

    @NotEmpty(message = "Required!")
    private String adminName;

    @Email(message = "Invalid email format")
    private String adminEmail;

    @NotEmpty(message = "Required!")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
}
