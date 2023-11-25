package com.education.module.quiz.model.query;

import com.education.common.model.query.EducationQueryModel;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import static com.education.module.quiz.constraint.QuizConstraint.NAME_MAX_SIZE;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class QuizQueryModel extends EducationQueryModel {

    @Size(max = NAME_MAX_SIZE)
    private String name;

}
