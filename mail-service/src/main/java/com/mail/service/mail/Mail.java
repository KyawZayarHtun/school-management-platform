package com.mail.service.mail;

import com.mail.service.mail.dto.MailReq;
import com.mail.service.mail.dto.Recipient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Mail implements Serializable {

    @Serial
    private static final long serialVersionUID = -4391778386645557573L;

    @Id
    private String id;

    private String subject, body;

    private Set<Recipient> recipients, cc, bcc;

    private Set<String> attachments;

    @CreatedDate
    private LocalDateTime createdAt;

    public Mail(MailReq mailReq) {
        this.subject = mailReq.getSubject();
        this.recipients = mailReq.getRecipients();
        this.body = mailReq.getBody();
        this.cc = mailReq.getCc();
        this.bcc = mailReq.getBcc();
        this.attachments = mailReq.getAttachments();
    }
}
