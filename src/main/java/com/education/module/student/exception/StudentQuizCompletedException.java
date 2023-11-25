package com.education.module.student.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
public class StudentQuizCompletedException extends StudentUncheckedException {

    private static final String MESSAGE = "%d student already completed %d quiz.";

    public StudentQuizCompletedException(long studentId, long quizId) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, MESSAGE.formatted(studentId, quizId));
    }

}
