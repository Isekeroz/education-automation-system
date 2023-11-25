package com.education.module.quiz.mapper;

import com.education.common.mapper.EducationMapper;
import com.education.module.quiz.entity.Quiz;
import com.education.module.quiz.model.QuizModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuizMapper extends EducationMapper<QuizModel, Quiz> {
}
