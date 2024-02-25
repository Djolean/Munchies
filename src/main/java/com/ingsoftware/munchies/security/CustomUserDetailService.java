package com.ingsoftware.munchies.security;

import com.ingsoftware.munchies.exception.Exception;
import com.ingsoftware.munchies.model.Admin;
import com.ingsoftware.munchies.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor

public class CustomUserDetailService implements UserDetailsService {
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Admin admin = adminRepository
                .findByAdminEmail(email)
                .orElseThrow(Exception.UserNotFoundException::new);
        if (!admin.isEnabled()) {
            throw new Exception.UserNotConfirmedYet();
        }
        return new org.springframework.security.core.userdetails.User(
                admin.getAdminEmail(), admin.getPassword(), new ArrayList<>());
    }
}
