package com.student.service.student;

import com.student.service.BaseField;
import com.student.service.student.dto.StudentProfileUpdateReq;
import com.student.service.student.dto.StudentRegistrationReq;
import com.student.service.util.constant.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Student extends BaseField implements Serializable {

    @Serial
    private static final long serialVersionUID = -2036048486896396392L;

    private String name, email, phone, password, profileImagePath;
    private Boolean isLocked, isActivated;
    private Gender gender;
    private LocalDateTime dob;

    public Student(StudentRegistrationReq request) {
        this.name = request.name();
        this.email = request.email();
        this.phone = request.phone();
        this.password = request.password();
        this.isLocked = true;
        this.isActivated = false;
    }

    public Student(StudentProfileUpdateReq request) {
        this.name = request.name();
        this.phone = request.phone();
        this.profileImagePath = request.profileImagePath();
        this.gender = request.gender();
        this.dob = Optional.ofNullable(request.dob())
                        .map(LocalDate::atStartOfDay)
                        .orElse(null);
    }
}