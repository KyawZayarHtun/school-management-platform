package com.student.service.config;

import com.student.service.student.StudentHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration(proxyBeanMethods = false)
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(StudentHandler studentHandler) {
        RequestPredicate acceptType = accept(APPLICATION_JSON);
        return nest(path("/students"), route()
                .GET("", acceptType, studentHandler::studentList) // For Admin
                .POST("", acceptType, studentHandler::registerStudent)
                .PUT("/{id}", acceptType, studentHandler::updateStudentProfile)
                .GET("/profile/{id}", acceptType, studentHandler::getStudentProfile)
                .GET("/{id}", acceptType, studentHandler::getStudentById) // For Admin
                .POST("/all", acceptType, studentHandler::createStudents) // Demo Purpose
                .build());
    }

}
