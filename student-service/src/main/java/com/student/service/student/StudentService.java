package com.student.service.student;

import com.student.service.student.dto.StudentDetail;
import com.student.service.student.dto.StudentProfile;
import com.student.service.student.dto.StudentProfileUpdateReq;
import com.student.service.student.dto.StudentRegistrationReq;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public record StudentService(StudentRepository studentRepository) {

    Flux<StudentDetail> studentList() {
        return studentRepository
                .findAll()
                .map(StudentDetail::new);
    }

    Mono<StudentDetail> registerStudent(Mono<StudentRegistrationReq> studentRegistrationRequest) {
        return studentRegistrationRequest
                .map(Student::new)
                .flatMap(studentRepository::save)
                .map(StudentDetail::new);
    }

    Flux<StudentDetail> insertAllStudents(Flux<Student> students) {
        return studentRepository.saveAll(students).map(StudentDetail::new);
    }

    Mono<StudentProfile> updateStudentProfile(String id, Mono<StudentProfileUpdateReq> studentProfileUpdateReq) {
        Mono<Student> existingProfile = studentRepository.findById(id);
        return existingProfile.zipWith(studentProfileUpdateReq)
                .flatMap(tuple -> {
                    Student student = tuple.getT1();
                    StudentProfileUpdateReq updateReq = tuple.getT2();

                    if (updateReq.name() != null && !updateReq.name().isBlank())
                        student.setName(updateReq.name());

                    if (updateReq.phone() != null && !updateReq.phone().isBlank())
                        student.setPhone(updateReq.phone());

                    if (updateReq.profileImagePath() != null && !updateReq.profileImagePath().isBlank())
                        student.setProfileImagePath(updateReq.profileImagePath());

                    if (updateReq.gender() != null)
                        student.setGender(updateReq.gender());

                    if (updateReq.dob() != null)
                        student.setDob(updateReq.dob().atStartOfDay());

                    return studentRepository.save(student);
                })
                .map(StudentProfile::new);
    }

    Mono<StudentDetail> findStudentById(String id) {
        return studentRepository
                .findById(id)
                .map(StudentDetail::new);
    }

    Mono<StudentProfile> findStudentProfileById(String id) {
        return studentRepository
                .findById(id)
                .map(StudentProfile::new);
    }

}
