package com.mail.service.mail.dto;

import com.mail.service.mail.Mail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailDetail {

    private String id, subject, body;

    private Set<Recipient> recipients, cc, bcc;

    private Set<String> attachments;

    private LocalDateTime createdAt;

    public MailDetail(Mail mail) {
        this.id = mail.getId();
        this.subject = mail.getSubject();
        this.body = mail.getBody();
        this.recipients = mail.getRecipients();
        this.cc = mail.getCc();
        this.bcc = mail.getBcc();
        this.attachments = mail.getAttachments();
        this.createdAt = mail.getCreatedAt();
    }
}
