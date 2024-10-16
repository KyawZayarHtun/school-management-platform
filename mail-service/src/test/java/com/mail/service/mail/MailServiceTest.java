package com.mail.service.mail;

import com.mail.service.mail.dto.MailReq;
import com.mail.service.mail.dto.Recipient;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.util.Set;

@SpringBootTest
public class MailServiceTest {



    /*@Test
    void testingMimeMail() throws MessagingException, UnsupportedEncodingException {
        var baseMail = MailReq.builder()
                .recipients(Set.of(new Recipient("naymyo4892@gmail.com", null)))
                .subject("fa2fa2")
                .body("""
                        <!DOCTYPE html>
                        <html lang="en">
                        <head>
                            <meta charset="UTF-8">
                            <meta name="viewport" content="width=device-width, initial-scale=1.0">
                            <title>Email Template</title>
                        </head>
                        <body style="margin: 0; padding: 0; background-color: #f7f7f7;">
                            <div style="max-width: 600px; margin: auto; background-color: #ffffff; border-radius: 8px; overflow: hidden;">
                                <div style="background-color: #4a90e2; padding: 20px; text-align: center;">
                                    <h1 style="color: white; margin: 0;">Welcome to Our Service!</h1>
                                </div>
                                <div style="padding: 20px;">
                                    <p style="font-size: 16px; color: #333333;">Hello,</p>
                                    <p style="font-size: 16px; color: #333333;">
                                        Thank you for signing up for our service. We are excited to have you on board.
                                    </p>
                                    <p style="font-size: 16px; color: #333333;">
                                        To get started, please visit our website:
                                    </p>
                                    <a href="https://example.com" style="display: inline-block; margin-top: 10px; padding: 10px 20px; background-color: #4a90e2; color: white; text-decoration: none; border-radius: 4px;">Get Started</a>
                                </div>
                                <div style="background-color: #f7f7f7; padding: 10px; text-align: center;">
                                    <p style="font-size: 12px; color: #999999;">&copy; 2024 Your Company. All rights reserved.</p>
                                </div>
                            </div>
                        </body>
                        </html>
                        """)
                .build();
        mailService.sendSimpleMail(baseMail);
    }*/

}
