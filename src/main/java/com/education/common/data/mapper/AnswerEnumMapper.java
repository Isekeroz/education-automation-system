package com.education.common.data.mapper;

import com.education.common.data.Answer;
import com.education.common.enumeration.converter.EducationEnumAttributeConverter;

public class AnswerEnumMapper extends EducationEnumAttributeConverter<Answer, Integer> {

    protected AnswerEnumMapper() {
        super(Answer.class);
    }

}
