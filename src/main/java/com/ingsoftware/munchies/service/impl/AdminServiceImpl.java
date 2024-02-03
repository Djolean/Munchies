package com.ingsoftware.munchies.service.impl;

import com.ingsoftware.munchies.controller.request.AdminRequestDTO;
import com.ingsoftware.munchies.controller.response.AdminResponseDTO;
import com.ingsoftware.munchies.exception.Exception;
import com.ingsoftware.munchies.mapper.AdminMapper;
import com.ingsoftware.munchies.model.entity.Admin;
import com.ingsoftware.munchies.model.entity.AdminVerificationToken;
import com.ingsoftware.munchies.model.entity.Restaurant;
import com.ingsoftware.munchies.repository.AdminRepository;
import com.ingsoftware.munchies.repository.AdminVerificationTokenRepository;
import com.ingsoftware.munchies.service.AdminService;
import com.ingsoftware.munchies.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.stereotype.Service;

import java.io.NotActiveException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminMapper mapper;
    private final EmailService emailService;
    private final AdminVerificationTokenRepository tokenRepository;

    @Override
    @Transactional
    public AdminResponseDTO addAdmin(AdminRequestDTO request) throws MessagingException {
        if (adminRepository.existsByAdminEmail(request.getAdminEmail())) {
            throw new Exception.UserAlreadyExists();
        } else {
            String encodedPassword = passwordEncoder.encode(request.getPassword());
            request.setPassword(encodedPassword);
        }
        Admin admin  = mapper.mapToEntity(request);
        admin.setCreatedDate(Instant.now());
        admin.setLastModifiedDate(Instant.now());

        AdminVerificationToken verificationToken = new AdminVerificationToken();
        verificationToken.setAdmin(admin);
        tokenRepository.save(verificationToken);

        emailService.sendMail(admin, verificationToken);
        return mapper.mapToDTO(adminRepository.save(admin));
    }

    @Override
    public void updateAdmin(String id, AdminRequestDTO request) {

        Admin admin = adminRepository.findById(id).orElseThrow(Exception.UserNotFoundException::new);

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        admin.setPassword(encodedPassword);
        admin.setLastModifiedDate(Instant.now());

        adminRepository.save(mapper.mapToEntityUpdate(admin, request));
    }
    @Override
    public AdminResponseDTO getAdminDetails() {
        return getLoggedInAdmin();
    }

    @Override
    public void deleteAdmin(String id) {
        Admin admin = adminRepository.findById(id).orElseThrow(Exception.UserNotFoundException::new);
        adminRepository.delete(admin);
    }

    @Override
    public AdminResponseDTO findById(String id) {
        Admin admin = adminRepository.findById(id).orElseThrow(Exception.UserNotFoundException::new);
        return mapper.mapToDTO(admin);
    }

    @Override
    public AdminResponseDTO getLoggedInAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return mapper.mapToDTO(adminRepository.findByAdminEmail(email).orElseThrow(Exception.UserNotFoundException::new));
    }

    @Override
    public List<AdminResponseDTO> findAll() {
        return adminRepository.findAll().stream().map(mapper::mapToDTO).toList();
    }


    @Transactional
    @Override
    public void verifyAccount(String token) {
        AdminVerificationToken verificationToken = tokenRepository.findByToken(token);

        if (verificationToken.getExpiryDate().isBefore(Instant.now())){
            verificationToken.setUsed(true);
        }
        if (!verificationToken.isUsed()) {
            Admin admin = verificationToken.getAdmin();
            admin.setEnabled(true);
            adminRepository.save(admin);

            verificationToken.setUsed(true);
            tokenRepository.save(verificationToken);
        } else {
            throw new Exception.UserAlreadyConfirmed();
        }
    }
}
