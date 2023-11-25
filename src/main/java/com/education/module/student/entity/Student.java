package com.education.module.student.entity;

import com.education.common.entity.EducationEntity;
import com.education.module.quiz.entity.Quiz;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

import static com.education.module.student.constraint.StudentConstraint.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = STUDENT_TABLE_NAME)
@SequenceGenerator(name = EducationEntity.SEQUENCE_GENERATOR_NAME, sequenceName = STUDENT_SEQUENCE_NAME, allocationSize = 10)
public class Student extends EducationEntity {

    @Column(name = NAME_COLUMN, nullable = false)
    @NotBlank
    @Size(max = NAME_MAX_SIZE)
    private String name;

    @Column(name = SURNAME_COLUMN, nullable = false)
    @NotBlank
    @Size(max = SURNAME_MAX_SIZE)
    private String surname;

    @Column(name = EMAIL_COLUMN, nullable = false, unique = true)
    @NotBlank
    @Size(max = EMAIL_MAX_SIZE)
    @Email
    private String email;

    @ManyToMany
    @PrimaryKeyJoinColumn
    @JoinTable(name = STUDENT_QUIZ_TABLE_NAME, joinColumns = @JoinColumn(name = STUDENT_ID_COLUMN)
            , inverseJoinColumns = @JoinColumn(name = QUIZ_ID_COLUMN))
    private List<Quiz> quizzes;

    @ElementCollection
    @CollectionTable(name = STUDENT_QUIZ_TABLE_NAME, joinColumns = @JoinColumn(name = STUDENT_ID_COLUMN))
    @Column(name = QUIZ_ID_COLUMN)
    private List<Long> quizIds;

}
