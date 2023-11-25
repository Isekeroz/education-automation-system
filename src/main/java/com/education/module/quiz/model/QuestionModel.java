package com.education.module.quiz.model;

import com.education.common.data.Answer;
import com.education.common.data.mapper.AnswerEnumMapper;
import com.education.common.model.EducationModel;
import jakarta.persistence.Convert;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import static com.education.module.quiz.constraint.QuizConstraint.OPTION_MAX_SIZE;
import static com.education.module.quiz.constraint.QuizConstraint.QUESTION_SENTENCE_MAX_SIZE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class QuestionModel extends EducationModel {

    @NotBlank
    @Size(max = QUESTION_SENTENCE_MAX_SIZE)
    private String questionSentence;

    @NotBlank
    @Size(max = OPTION_MAX_SIZE)
    private String firstOption;

    @NotBlank
    @Size(max = OPTION_MAX_SIZE)
    private String secondOption;

    @NotBlank
    @Size(max = OPTION_MAX_SIZE)
    private String thirdOption;

    @NotNull
    @Convert(converter = AnswerEnumMapper.class)
    private Answer correctOption;

}
