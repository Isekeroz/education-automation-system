package com.education.module.student.entity;

import com.education.common.data.Answer;
import com.education.common.data.mapper.AnswerEnumMapper;
import com.education.common.entity.EducationEntity;
import com.education.module.quiz.entity.Question;
import com.education.module.quiz.entity.Quiz;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import static com.education.module.student.constraint.StudentConstraint.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = STUDENT_QUIZ_ANSWER_TABLE_NAME)
@SequenceGenerator(name = EducationEntity.SEQUENCE_GENERATOR_NAME, sequenceName = STUDENT_QUIZ_ANSWER_SEQUENCE_NAME, allocationSize = 10)
public class StudentQuizAnswer extends EducationEntity {

    @NotNull
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId(value = STUDENT_MAPS_ID)
    @JoinColumn(name = STUDENT_ID_COLUMN, insertable = false, updatable = false)
    private Student student;

    @Column(name = STUDENT_ID_COLUMN)
    private Long studentId;

    @NotNull
    @OneToOne
    @MapsId(value = QUIZ_MAPS_ID)
    @JoinColumn(name = QUIZ_ID_COLUMN, insertable = false, updatable = false)
    private Quiz quiz;

    @Column(name = QUIZ_ID_COLUMN)
    private Long quizId;

    @NotNull
    @OneToOne
    @MapsId(value = QUESTION_MAPS_ID)
    @JoinColumn(name = QUESTION_ID_COLUMN, insertable = false, updatable = false)
    private Question question;

    @Column(name = QUESTION_ID_COLUMN)
    private Long questionId;

    @Column(name = ANSWER_COLUMN, nullable = false)
    @NotNull
    @Convert(converter = AnswerEnumMapper.class)
    private Answer answer;

}
