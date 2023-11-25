package com.education.module.student.model;

import com.education.common.model.EducationModel;
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
public class StudentQuizResultModel extends EducationModel {

    private Integer questionCount;

    private Integer answeredQuestionCount;

    private Integer correctAnswersCount;

    private Integer incorrectAnswersCount;

}
