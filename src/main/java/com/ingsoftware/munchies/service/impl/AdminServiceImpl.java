package com.ingsoftware.munchies.service.impl;

import com.ingsoftware.munchies.exception.Exception;
import com.ingsoftware.munchies.model.entity.Admin;
import com.ingsoftware.munchies.repository.AdminRepository;
import com.ingsoftware.munchies.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Admin saveAdmin(Admin admin) {
        if (AdminRepository.existsByAdminEmail(admin.getAdminEmail())) {
            throw new Exception.UserAlreadyExists();
        } else {
            String encodedPassword = passwordEncoder.encode(admin.getPassword());
            admin.setPassword(encodedPassword);
        }
        return adminRepository.save(admin);
    }

    public Admin updateAdmin(Admin admin) {
        Admin loggedInUser = getLoggedInAdmin();
        loggedInUser.setAdminName(admin.getAdminName());
        loggedInUser.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(loggedInUser);
    }

    public Admin getAdminDetails() {
        return getLoggedInAdmin();
    }

    public void deleteAdmin() {
        Admin admin = getLoggedInAdmin();
        adminRepository.delete(admin);
    }

    public Admin getLoggedInAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return adminRepository.findByAdminEmail(email).orElseThrow(Exception.UserNotFoundException::new);
    }
}
