package com.education.module.student.model;

import com.education.common.data.Answer;
import com.education.common.model.EducationModel;
import com.education.module.quiz.model.QuestionModel;
import com.education.module.student.model.view.StudentView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class StudentQuestionAnswerModel extends EducationModel {

    @JsonView(value = StudentView.Query.class)
    private QuestionModel question;

    @JsonView(value = StudentView.Command.class)
    @NotNull
    private Long questionId;

    @NotNull
    private Answer answer;

}
