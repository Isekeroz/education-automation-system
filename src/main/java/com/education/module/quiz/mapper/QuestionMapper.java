package com.education.module.quiz.mapper;

import com.education.common.mapper.EducationMapper;
import com.education.module.quiz.entity.Question;
import com.education.module.quiz.model.QuestionModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper extends EducationMapper<QuestionModel, Question> {
}
