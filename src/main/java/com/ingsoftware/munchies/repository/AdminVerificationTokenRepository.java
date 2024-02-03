package com.ingsoftware.munchies.repository;

import com.ingsoftware.munchies.model.entity.Admin;
import com.ingsoftware.munchies.model.entity.AdminVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminVerificationTokenRepository extends JpaRepository<AdminVerificationToken, String> {
    AdminVerificationToken findByToken(String token);
}
