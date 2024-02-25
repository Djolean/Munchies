package com.ingsoftware.munchies.service;

import com.ingsoftware.munchies.model.Admin;
import com.ingsoftware.munchies.model.AdminVerificationToken;
import com.ingsoftware.munchies.model.PasswordResetToken;
import jakarta.mail.MessagingException;

public interface EmailService {

    void sendResetPasswordEmail(String email, PasswordResetToken token) throws MessagingException;

    void sendMail(Admin user, AdminVerificationToken token) throws MessagingException;
}
