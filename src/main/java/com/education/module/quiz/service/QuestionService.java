package com.education.module.quiz.service;

import com.education.common.model.constraint.validation.EducationConstraintValidationGroups;
import com.education.module.quiz.model.QuestionModel;
import com.education.module.quiz.model.query.QuestionQueryModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

@Validated
public interface QuestionService {

    QuestionModel save(@Valid @NotNull QuestionModel questionModel);

    @Validated(value = {Default.class, EducationConstraintValidationGroups.ModifyingOperation.class})
    QuestionModel update(@Valid @NotNull QuestionModel questionModel);

    void deleteById(long id);

    QuestionModel getById(long id);

    Page<QuestionModel> queryPage(@Valid @NotNull QuestionQueryModel questionQueryModel);

}
