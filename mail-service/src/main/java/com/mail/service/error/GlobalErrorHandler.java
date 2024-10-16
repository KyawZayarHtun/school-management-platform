package com.mail.service.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalErrorHandler{


    @ExceptionHandler(ValidationErr.class)
    private ResponseEntity<Map<String, String>> handleValidationError(ValidationErr ex) {
        return ResponseEntity.badRequest().body(ex.getMessages());
    }


}
