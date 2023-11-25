package com.education.module.quiz.model.query;

import com.education.common.model.query.EducationQueryModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class QuestionQueryModel extends EducationQueryModel {
}
