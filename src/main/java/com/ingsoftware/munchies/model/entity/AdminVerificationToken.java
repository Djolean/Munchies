package com.ingsoftware.munchies.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "admin_verification_token")
public class AdminVerificationToken {

        @Id
        @Column(name = "token_id")
        @GeneratedValue(strategy = GenerationType.UUID)
        private String id;

        @Column(nullable = false, unique = true)
        private String token;

        @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JoinColumn(nullable = false, name = "admin_id")
        private Admin admin;

        @Column(nullable = false)
        private boolean used;

        @Column(nullable = false)
        private Instant expiryDate;


        public AdminVerificationToken() {
            this.token = UUID.randomUUID().toString();
            this.used = false;
            this.expiryDate = Instant.now().plusSeconds(86400); // Token is valid for 24 hours
        }
}
