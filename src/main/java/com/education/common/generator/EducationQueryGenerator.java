package com.education.common.generator;

import com.education.common.model.query.EducationQueryModel;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.EntityPathBase;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface EducationQueryGenerator<QM extends EducationQueryModel, EPB extends EntityPathBase<?>> {

    Predicate generate(@NotNull QM queryModel, @NotNull EPB query);

}
