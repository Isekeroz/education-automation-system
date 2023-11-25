package com.education.module.student.model;

import com.education.common.model.EducationModel;
import com.education.module.quiz.model.QuizModel;
import com.education.module.student.model.view.StudentView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class StudentQuizAnswerModel extends EducationModel {

    @JsonView(value = StudentView.Query.class)
    private StudentModel student;

    @JsonView(value = StudentView.Command.class)
    @NotNull
    private Long studentId;

    @JsonView(value = StudentView.Query.class)
    private QuizModel quiz;

    @JsonView(value = StudentView.Command.class)
    @NotNull
    private Long quizId;

    @NotNull
    private List<StudentQuestionAnswerModel> questionAnswers;

}
