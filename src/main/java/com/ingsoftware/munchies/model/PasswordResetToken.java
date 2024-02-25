package com.ingsoftware.munchies.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "password_reset_token")
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    @Column(nullable = false)
    private boolean used;

    public PasswordResetToken() {
        this.token = UUID.randomUUID().toString();
        this.used = false;
        this.expiryDate = Instant.now().plusSeconds(1800); // Token is valid for 30 minutes
    }
}
