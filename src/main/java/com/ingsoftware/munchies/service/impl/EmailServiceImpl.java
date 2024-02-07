package com.ingsoftware.munchies.service.impl;


import com.ingsoftware.munchies.model.entity.Admin;
import com.ingsoftware.munchies.model.entity.AdminVerificationToken;
import com.ingsoftware.munchies.model.entity.PasswordResetToken;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ingsoftware.munchies.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {


    private final TemplateEngine templateEngine;

    private final JavaMailSender javaMailSender;

    @Override
    public void sendMail(Admin user, AdminVerificationToken token) throws MessagingException {
        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("token", token.getToken());

        String emailBody = templateEngine.process("emails/welcome", context);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Welcome " + user.getAdminName());
        helper.setText(emailBody, true);
        helper.setTo(user.getAdminEmail());
        javaMailSender.send(mimeMessage);
    }

    @Override
    public void sendResetPasswordEmail(String email, PasswordResetToken token) throws MessagingException {
        Context context = new Context();
        context.setVariable("email", email);
        context.setVariable("token", token.getToken());

        String emailBody = templateEngine.process("emails/reset-password", context);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Password Reset Request");
        helper.setText(emailBody, true);
        helper.setTo(email);
        javaMailSender.send(mimeMessage);
    }
}
