package com.ingsoftware.munchies.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.*;

@Entity
@Getter
@Setter
@Builder
@Table(name = "admin")
@AllArgsConstructor
@RequiredArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private String adminId;

    @Column(nullable = false)
    private String adminName;

    @Column(nullable = false)
    private String adminEmail;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, updatable = false)
    private Instant createdDate;

    @Column(nullable = false)
    private Instant lastModifiedDate;

    @Column(nullable = false, name = "isenabled")
    private boolean isEnabled;

    @OneToOne(mappedBy = "admin", cascade = CascadeType.ALL)
    private AdminVerificationToken verificationToken;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<PasswordResetToken> passwordResetTokens = new ArrayList<>();

}