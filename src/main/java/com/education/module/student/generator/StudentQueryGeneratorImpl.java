package com.education.module.student.generator;

import com.education.common.generator.EducationQueryGenerator;
import com.education.module.student.entity.QStudent;
import com.education.module.student.model.query.StudentQueryModel;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component(value = "studentQueryGenerator")
public class StudentQueryGeneratorImpl implements EducationQueryGenerator<StudentQueryModel, QStudent> {

    @Override
    public Predicate generate(@NotNull StudentQueryModel queryModel, @NotNull QStudent query) {
        final BooleanBuilder expressionBuilder = new BooleanBuilder();

        final String name = queryModel.getName();
        if (StringUtils.isNotBlank(name)) {
            expressionBuilder.and(query.name.eq(name));
        }

        final String surname = queryModel.getSurname();
        if (StringUtils.isNotBlank(surname)) {
            expressionBuilder.and(query.surname.eq(surname));
        }

        final String email = queryModel.getEmail();
        if (StringUtils.isNotBlank(email)) {
            expressionBuilder.and(query.email.eq(email));
        }

        return expressionBuilder;
    }

}
