package com.ingsoftware.munchies.service;

import com.ingsoftware.munchies.model.entity.Admin;
import com.ingsoftware.munchies.model.entity.AdminVerificationToken;
import jakarta.mail.MessagingException;

public interface EmailService {

    String sendMail(Admin user, AdminVerificationToken token) throws MessagingException;
}
