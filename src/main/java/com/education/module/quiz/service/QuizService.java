package com.education.module.quiz.service;

import com.education.common.model.constraint.validation.EducationConstraintValidationGroups;
import com.education.module.quiz.model.QuizModel;
import com.education.module.quiz.model.QuizQuestionModel;
import com.education.module.quiz.model.query.QuizQueryModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface QuizService {

    QuizModel save(@Valid @NotNull QuizModel quizModel);

    @Validated(value = {Default.class, EducationConstraintValidationGroups.ModifyingOperation.class})
    QuizModel update(@Valid @NotNull QuizModel quizModel);

    void addQuestion(@Valid @NotNull QuizQuestionModel quizQuestionModel);

    void deleteQuestion(@Valid @NotNull QuizQuestionModel quizQuestionModel);

    void deleteById(long id);

    QuizModel getById(long id);

    List<QuizModel> queryList(@Valid @NotNull QuizQueryModel quizQueryModel);

    Page<QuizModel> queryPage(@Valid @NotNull QuizQueryModel quizQueryModel);

    int getQuestionCountById(long quizId);

}
