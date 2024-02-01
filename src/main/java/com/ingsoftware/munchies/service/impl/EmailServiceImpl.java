package com.ingsoftware.munchies.service.impl;


import com.ingsoftware.munchies.model.entity.Admin;
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
    public String sendMail(Admin user) throws MessagingException {
        Context context = new Context();
        context.setVariable("user", user);

        String process = templateEngine.process("/welcome", context);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Welcome " + user.getAdminName());
        helper.setText(process, true);
        helper.setTo(user.getAdminEmail());
        javaMailSender.send(mimeMessage);
        return "Sent";
    }
}
