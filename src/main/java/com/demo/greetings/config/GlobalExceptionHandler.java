package com.demo.greetings.config;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demo.greetings.domain.NameAlreadyExistException;

public class GlobalExceptionHandler {
    @ExceptionHandler(NameAlreadyExistException.class)
    ProblemDetail nameAlreadyExistsException(NameAlreadyExistException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        problemDetail.setTitle("Name Not Accepted!");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

}
