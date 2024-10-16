package com.mail.service.mail.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record Recipient(
        @Email(message = "Invalid Email address include!")
        @NotBlank(message = "Email must be include!")
        String mail,
        String personalName
) {
}
