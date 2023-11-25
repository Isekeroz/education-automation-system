package com.education.module.quiz.generator;

import com.education.common.generator.EducationQueryGenerator;
import com.education.module.quiz.entity.QQuiz;
import com.education.module.quiz.model.query.QuizQueryModel;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component(value = "quizQueryGenerator")
public class QuizQueryGeneratorImpl implements EducationQueryGenerator<QuizQueryModel, QQuiz> {

    @Override
    public Predicate generate(@NotNull QuizQueryModel queryModel, @NotNull QQuiz query) {
        final BooleanBuilder expressionBuilder = new BooleanBuilder();
        final String name = queryModel.getName();
        if (StringUtils.isNotBlank(name)) {
            expressionBuilder.and(query.name.eq(name));
        }
        return expressionBuilder;
    }

}
