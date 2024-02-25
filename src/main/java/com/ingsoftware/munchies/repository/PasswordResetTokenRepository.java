package com.ingsoftware.munchies.repository;

import com.ingsoftware.munchies.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, String> {
    PasswordResetToken findByToken(String token);

    void deleteByUsed(boolean value);

    void deleteByExpiryDateBefore(Instant value);
}

