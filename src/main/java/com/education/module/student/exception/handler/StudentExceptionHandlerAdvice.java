package com.education.module.student.exception.handler;

import com.education.module.student.exception.StudentUncheckedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentExceptionHandlerAdvice {

    @ExceptionHandler(StudentUncheckedException.class)
    public ResponseEntity<?> handleException(StudentUncheckedException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

}
