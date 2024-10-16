package com.mail.service.error;

import lombok.Getter;
import org.springframework.validation.BeanPropertyBindingResult;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

@Getter
public class ValidationErr extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 8813564514690845346L;

    private final Map<String, String> messages = new HashMap<>();

    public ValidationErr(BeanPropertyBindingResult result) {
        for (var fe : result.getFieldErrors()) {
            messages.put(fe.getField(), fe.getDefaultMessage());
        }

    }
}
