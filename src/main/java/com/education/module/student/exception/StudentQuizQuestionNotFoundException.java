package com.education.module.student.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
public class StudentQuizQuestionNotFoundException extends StudentUncheckedException {

    private static final String MESSAGE = "%d quiz hasn't %d question.";

    public StudentQuizQuestionNotFoundException(long quizId, long questionId) {
        super(HttpStatus.NOT_FOUND, MESSAGE.formatted(quizId, questionId));
    }

}
