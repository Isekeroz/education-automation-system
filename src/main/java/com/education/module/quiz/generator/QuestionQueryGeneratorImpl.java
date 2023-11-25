package com.education.module.quiz.generator;

import com.education.common.generator.EducationQueryGenerator;
import com.education.module.quiz.entity.QQuestion;
import com.education.module.quiz.model.query.QuestionQueryModel;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component(value = "questionQueryGenerator")
public class QuestionQueryGeneratorImpl implements EducationQueryGenerator<QuestionQueryModel, QQuestion> {

    @Override
    public Predicate generate(@NotNull QuestionQueryModel queryModel, @NotNull QQuestion query) {
        return new BooleanBuilder();
    }

}
