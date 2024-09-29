package com.student.service.student.dto;

import com.student.service.student.Student;
import com.student.service.util.constant.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetail {

    private String id, name, email, phone, profileImagePath;
    private Boolean isLocked, isActivated;
    private Gender gender;
    private LocalDate dob;
    private LocalDateTime createdAt, updatedAt;

    public StudentDetail(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.email = student.getEmail();
        this.phone = student.getPhone();
        this.profileImagePath = student.getProfileImagePath();
        this.isLocked = student.getIsLocked();
        this.isActivated = student.getIsActivated();
        this.gender = student.getGender();
        this.dob = Optional.ofNullable(student.getDob())
                        .map(LocalDateTime::toLocalDate)
                        .orElse(null);
        this.createdAt = student.getCreatedAt();
        this.updatedAt = student.getUpdatedAt();
    }
}
