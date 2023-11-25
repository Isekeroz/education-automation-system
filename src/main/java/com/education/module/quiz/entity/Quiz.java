package com.education.module.quiz.entity;

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

import java.time.OffsetDateTime;
import java.util.List;

import static com.education.module.quiz.constraint.QuizConstraint.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = QUIZ_TABLE_NAME)
@SequenceGenerator(name = EducationEntity.SEQUENCE_GENERATOR_NAME, sequenceName = QUIZ_SEQUENCE_NAME, allocationSize = 10)
public class Quiz extends EducationEntity {

    @Column(name = NAME_COLUMN, nullable = false, unique = true)
    @NotBlank
    @Size(max = NAME_MAX_SIZE)
    private String name;

    @Column(name = START_DATE_COLUMN)
    private OffsetDateTime startDate;

    @Column(name = END_DATE_COLUMN)
    private OffsetDateTime endDate;

    @Column(name = ACTIVE_COLUMN)
    private boolean active = true;

    @ManyToMany
    @PrimaryKeyJoinColumn
    @JoinTable(name = QUIZ_QUESTION_TABLE_NAME, joinColumns = @JoinColumn(name = QUIZ_ID_COLUMN)
            , inverseJoinColumns = @JoinColumn(name = QUESTION_ID_COLUMN))
    private List<Question> questions;

    @ElementCollection
    @CollectionTable(name = QUIZ_QUESTION_TABLE_NAME, joinColumns = @JoinColumn(name = QUIZ_ID_COLUMN))
    @Column(name = QUESTION_ID_COLUMN)
    @NotNull
    @Size(min = QUESTION_MIN_SIZE)
    private List<Long> questionIds;

}
