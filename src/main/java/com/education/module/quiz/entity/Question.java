package com.education.module.quiz.entity;

import com.education.common.data.Answer;
import com.education.common.data.mapper.AnswerEnumMapper;
import com.education.common.entity.EducationEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import static com.education.module.quiz.constraint.QuizConstraint.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = QUESTION_TABLE_NAME)
@SequenceGenerator(name = EducationEntity.SEQUENCE_GENERATOR_NAME, sequenceName = QUESTION_SEQUENCE_NAME, allocationSize = 10)
public class Question extends EducationEntity {

    @Column(name = QUESTION_SENTENCE_COLUMN, nullable = false)
    @NotBlank
    @Size(max = QUESTION_SENTENCE_MAX_SIZE)
    private String questionSentence;

    @Column(name = FIRST_OPTION_COLUMN, nullable = false)
    @NotBlank
    @Size(max = OPTION_MAX_SIZE)
    private String firstOption;

    @Column(name = SECOND_OPTION_COLUMN, nullable = false)
    @NotBlank
    @Size(max = OPTION_MAX_SIZE)
    private String secondOption;

    @Column(name = THIRD_OPTION_COLUMN, nullable = false)
    @NotBlank
    @Size(max = OPTION_MAX_SIZE)
    private String thirdOption;

    @Column(name = CORRECT_OPTION_COLUMN, nullable = false)
    @NotNull
    @Convert(converter = AnswerEnumMapper.class)
    private Answer correctOption;

}
