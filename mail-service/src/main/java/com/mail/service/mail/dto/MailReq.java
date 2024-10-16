package com.mail.service.mail.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailReq {

    @Valid
    @NotEmpty(message = "Recipients must not be empty!")
    @Builder.Default
    private Set<Recipient> recipients = new HashSet<>();

    @NotBlank(message = "Subject must not be empty!")
    private String subject;

    @NotBlank(message = "body must not be empty!")
    private String body;

    @Valid
    @Builder.Default
    private Set<Recipient> cc = new HashSet<>();

    @Valid
    @Builder.Default
    private Set<Recipient> bcc = new HashSet<>();

    @Builder.Default
    private Set<String> attachments = new HashSet<>();

}
