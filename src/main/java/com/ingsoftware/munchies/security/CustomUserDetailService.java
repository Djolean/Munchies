package com.ingsoftware.munchies.security;

import java.util.ArrayList;

import com.ingsoftware.munchies.exception.Exception;
import com.ingsoftware.munchies.model.entity.Admin;
import com.ingsoftware.munchies.repository.AdminRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CustomUserDetailService implements UserDetailsService {
        private AdminRepository adminRepository;
        @Override
        public UserDetails loadUserByUsername(String email) {
            Admin admin = adminRepository
                    .findByAdminEmail(email)
                    .orElseThrow(Exception.UserNotFoundException::new);
            if(admin.isEnabled()) {
                return new org.springframework.security.core.userdetails.User(
                        admin.getAdminEmail(), admin.getPassword(), new ArrayList<>()
                );
            } else
                throw new Exception.UserNotConfirmedYet();
            }
        }