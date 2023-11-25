package com.education.module.quiz.model;

import com.education.common.model.EducationModel;
import com.education.module.quiz.model.view.QuizView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;
import java.util.List;

import static com.education.module.quiz.constraint.QuizConstraint.NAME_MAX_SIZE;
import static com.education.module.quiz.constraint.QuizConstraint.QUESTION_MIN_SIZE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class QuizModel extends EducationModel {

    @NotBlank
    @Size(max = NAME_MAX_SIZE)
    private String name;

    private OffsetDateTime startDate;

    private OffsetDateTime endDate;

    private boolean active;

    @JsonView(value = QuizView.Query.class)
    private List<QuestionModel> questions;

    @JsonView(value = QuizView.Command.class)
    @NotNull
    @Size(min = QUESTION_MIN_SIZE)
    private List<Long> questionIds;

}
