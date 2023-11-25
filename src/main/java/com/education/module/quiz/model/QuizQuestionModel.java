package com.education.module.quiz.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

import static com.education.module.quiz.constraint.QuizConstraint.QUESTION_MIN_SIZE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class QuizQuestionModel implements Serializable {

    @NotNull
    private Long quizId;

    @NotNull
    @Size(min = QUESTION_MIN_SIZE)
    private List<Long> questionIds;

}
