package com.ingsoftware.munchies.repository;

import com.ingsoftware.munchies.model.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, String> {
    PasswordResetToken findByToken(String token);
    List<PasswordResetToken> deleteByUsed(boolean value);
    List<PasswordResetToken> deleteByExpiryDateBefore(Instant value);
}

