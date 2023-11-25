package com.education.module.student.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
public class StudentQuizNotFoundException extends StudentUncheckedException {

    private static final String MESSAGE = "%d quiz not found.";

    public StudentQuizNotFoundException(long id) {
        super(HttpStatus.NOT_FOUND, MESSAGE.formatted(id));
    }

}
