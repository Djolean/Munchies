package com.ingsoftware.munchies.service;

import com.ingsoftware.munchies.model.entity.Admin;
import com.ingsoftware.munchies.model.entity.AdminVerificationToken;
import com.ingsoftware.munchies.model.entity.PasswordResetToken;
import jakarta.mail.MessagingException;

public interface EmailService {

    void sendResetPasswordEmail(String email, PasswordResetToken token) throws MessagingException;

    void sendMail(Admin user, AdminVerificationToken token) throws MessagingException;
}
