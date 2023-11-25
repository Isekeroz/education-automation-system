package com.education.module.quiz.service;

import com.education.common.model.constraint.validation.EducationConstraintValidationGroups;
import com.education.module.quiz.model.QuestionModel;
import com.education.module.quiz.model.query.QuestionQueryModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

@Validated
public interface QuestionService {

    QuestionModel save(@Valid @NotNull QuestionModel questionModel);

    QuestionModel update(@Validated(value = {EducationConstraintValidationGroups.ModifyingOperation.class})
                         @NotNull QuestionModel questionModel);

    void deleteById(long id);

    QuestionModel getById(long id);

    Page<QuestionModel> queryPage(@Valid @NotNull QuestionQueryModel questionQueryModel);

}
