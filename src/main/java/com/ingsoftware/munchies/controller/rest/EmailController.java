package com.ingsoftware.munchies.controller.rest;

import com.ingsoftware.munchies.model.Admin;
import com.ingsoftware.munchies.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/email")
@RequiredArgsConstructor
@Tag(name = "Email API")
@SecurityRequirement(name = "basicAuth")
public class EmailController {

    private final EmailService emailService;


    @Operation(summary = "Send welcome mail to new user")
    @ApiResponse(responseCode = "200", description = "Email Successfully sent!")
    @ApiResponse(responseCode = "500", description = "Failed to send email")
    @PostMapping
    public ResponseEntity<String> sendMail(@RequestBody Admin user) {
        try {
            emailService.sendMail(user, user.getVerificationToken());
            return ResponseEntity.ok().build();
        } catch (MessagingException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(500).body("Failed to send email: " + ex.getMessage());
        }
    }
}