package com.education.module.student.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class StudentUncheckedException extends RuntimeException {

    private HttpStatus status;

    public StudentUncheckedException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

}
