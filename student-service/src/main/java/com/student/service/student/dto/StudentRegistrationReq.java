package com.student.service.student.dto;

public record StudentRegistrationReq(
        String name,
        String email,
        String phone,
        String password
) {

}
