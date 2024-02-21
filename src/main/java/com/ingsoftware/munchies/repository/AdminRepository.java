package com.ingsoftware.munchies.repository;

import com.ingsoftware.munchies.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
    boolean existsByAdminEmail(String email);
    Optional<Admin> findByAdminEmail(String email);
    Optional<Admin> findByAdminNameAndPassword(String email, String password);
}
