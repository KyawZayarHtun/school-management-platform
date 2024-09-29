package com.student.service.student.dto;

import com.student.service.util.constant.Gender;

import java.time.LocalDate;

public record StudentProfileUpdateReq(
        String name,
        String phone,
        String profileImagePath,
        Gender gender,
        LocalDate dob
) {
}
