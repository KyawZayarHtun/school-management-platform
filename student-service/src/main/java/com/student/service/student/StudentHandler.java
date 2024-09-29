package com.student.service.student;

import com.student.service.student.dto.StudentProfile;
import com.student.service.student.dto.StudentProfileUpdateReq;
import com.student.service.student.dto.StudentRegistrationReq;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public record StudentHandler(StudentService studentService) {

    public Mono<ServerResponse> studentList(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(studentService.studentList(), Student.class);
    }

    public Mono<ServerResponse> registerStudent(ServerRequest serverRequest) {
        var student = serverRequest.bodyToMono(StudentRegistrationReq.class);
        return ServerResponse
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(studentService.registerStudent(student), StudentRegistrationReq.class)
                .switchIfEmpty(ServerResponse.badRequest().build());
    }


    // For Demo Purpose
    public Mono<ServerResponse> createStudents(ServerRequest request) {
        var students = request.bodyToFlux(Student.class);
        return ServerResponse.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(studentService.insertAllStudents(students), Student.class);
    }

    public Mono<ServerResponse> updateStudentProfile(ServerRequest serverRequest) {
        var studentUpdateReq = serverRequest.bodyToMono(StudentProfileUpdateReq.class);
        var studentId = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(studentService.updateStudentProfile(studentId, studentUpdateReq), StudentProfile.class);

    }

    public Mono<ServerResponse> getStudentById(ServerRequest serverRequest) {
        return studentService
                .findStudentById(serverRequest.pathVariable("id"))
                .flatMap(student -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(student), Student.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getStudentProfile(ServerRequest serverRequest) {
        return studentService
                .findStudentProfileById(serverRequest.pathVariable("id"))
                .flatMap(student -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(student), StudentProfile.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }



}
