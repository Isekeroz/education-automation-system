package com.education.module.student.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

import static com.education.module.student.constraint.StudentConstraint.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = STUDENT_QUIZ_TABLE_NAME)
public class StudentQuiz implements Serializable {

    @AttributeOverrides({
            @AttributeOverride(name = STUDENT_MAPS_ID, column = @Column(name = STUDENT_ID_COLUMN)),
            @AttributeOverride(name = QUIZ_MAPS_ID, column = @Column(name = QUIZ_ID_COLUMN))
    })
    @EmbeddedId
    private StudentQuizEmbeddable id;

    @Column(name = COMPLETED_COLUMN, nullable = false)
    private boolean completed = false;

}
