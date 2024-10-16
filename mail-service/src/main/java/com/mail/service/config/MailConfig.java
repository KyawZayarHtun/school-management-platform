package com.mail.service.config;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.UnsupportedEncodingException;

@Configuration
@RequiredArgsConstructor
public class MailConfig {

    @Value("${spring.mail.username}")
    private String from;

    @Value("${spring.mail.alias}")
    private String alias;

    private final JavaMailSender mailSender;

    @Bean
    public MimeMessage mimeMailMessage() {
        return mailSender.createMimeMessage();
    }

    @Bean
    public MimeMessageHelper mimeMailMessageHelper(MimeMessage mimeMailMessage) throws MessagingException, UnsupportedEncodingException {
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
        mimeMessageHelper.setFrom(from, alias);
        return mimeMessageHelper;
    }

}
