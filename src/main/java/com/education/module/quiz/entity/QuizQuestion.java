package com.education.module.quiz.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

import static com.education.module.quiz.constraint.QuizConstraint.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = QUIZ_QUESTION_TABLE_NAME)
public class QuizQuestion implements Serializable {

    @AttributeOverrides({
            @AttributeOverride(name = QUIZ_MAPS_ID, column = @Column(name = QUIZ_ID_COLUMN)),
            @AttributeOverride(name = QUESTION_MAPS_ID, column = @Column(name = QUESTION_ID_COLUMN))
    })
    @EmbeddedId
    private QuizQuestionEmbeddable id;

}
