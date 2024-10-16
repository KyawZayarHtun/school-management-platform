package com.mail.service.mail;

import com.mail.service.mail.dto.MailReq;
import com.mail.service.mail.dto.Recipient;
import com.mail.service.mail.dto.RecipientType;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.UnsupportedEncodingException;
import java.util.Set;

import static com.mail.service.mail.dto.RecipientType.*;

@Service
public record MailClient(
        JavaMailSender javaMailSender,
        MimeMessage mimeMessage,
        MimeMessageHelper mimeMessageHelper, // ERROR: might be an error for another method that us this bean
        @Value("${spring.mail.unsupportedHtmlRenderText}") String unsupportedText,
        MailService mailService
) {


    public Mono<Void> sendSimpleMail(MailReq mailReq) {

        return Mono.fromCallable(() -> {
                    addRecipientsWithType(mailReq.getRecipients(), TO);

                    mimeMessageHelper.setSubject(mailReq.getSubject());

                    mimeMessageHelper.setText(unsupportedText, mailReq.getBody());

                    if (!mailReq.getCc().isEmpty()) {
                        addRecipientsWithType(mailReq.getCc(), CC);
                    }

                    if (!mailReq.getBcc().isEmpty()) {
                        addRecipientsWithType(mailReq.getBcc(), BCC);
                    }

                    if (!mailReq.getAttachments().isEmpty())
                        this.setAttachments(mimeMessageHelper, mailReq.getAttachments());

                    javaMailSender.send(mimeMessage);
                    return null;
                })
                .subscribeOn(Schedulers.boundedElastic())
                .then(mailService.insertMail(Mono.just(mailReq)))
                .then();
    }

    private void addRecipientsWithType(Set<Recipient> recipients, RecipientType recipientType) throws MessagingException, UnsupportedEncodingException {
        switch (recipientType) {
            case TO -> {
                for (Recipient recipient : recipients)
                    mimeMessageHelper.addTo(recipient.mail(), recipient.personalName());
            }
            case CC -> {
                for (Recipient recipient : recipients)
                    mimeMessageHelper.addCc(recipient.mail(), recipient.personalName());
            }
            case BCC -> {
                for (Recipient recipient : recipients)
                    mimeMessageHelper.addBcc(recipient.mail(), recipient.personalName());
            }
        }

    }

    private void setAttachments(MimeMessageHelper mimeMessageHelper, Set<String> attachments) {
        System.out.println("attachments.size() = " + attachments.size());
    }

}
