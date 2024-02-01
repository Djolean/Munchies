package com.ingsoftware.munchies.service;

import com.ingsoftware.munchies.model.entity.Admin;
import jakarta.mail.MessagingException;

public interface EmailService {

    String sendMail(Admin user) throws MessagingException;
}
