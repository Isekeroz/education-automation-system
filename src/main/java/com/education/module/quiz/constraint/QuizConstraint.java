package com.education.module.quiz.constraint;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class QuizConstraint {

    // TABLE
    public static final String QUIZ_TABLE_NAME = "QUIZ";
    public static final String QUESTION_TABLE_NAME = "QUESTION";
    public static final String QUIZ_QUESTION_TABLE_NAME = "QUIZ_QUESTION";
    public static final String QUIZ_SEQUENCE_NAME = "QUIZ_SEQ";
    public static final String QUESTION_SEQUENCE_NAME = "QUESTION_SEQ";

    // COLUMN
    public static final String NAME_COLUMN = "NAME";
    public static final String START_DATE_COLUMN = "START_DATE";
    public static final String END_DATE_COLUMN = "END_DATE";
    public static final String ACTIVE_COLUMN = "ACTIVE";
    public static final String QUESTION_SENTENCE_COLUMN = "QUESTION_SENTENCE";
    public static final String FIRST_OPTION_COLUMN = "FIRST_OPTION";
    public static final String SECOND_OPTION_COLUMN = "SECOND_OPTION";
    public static final String THIRD_OPTION_COLUMN = "THIRD_OPTION";
    public static final String CORRECT_OPTION_COLUMN = "CORRECT_OPTION";
    public static final String QUIZ_ID_COLUMN = "QUIZ_ID";
    public static final String QUESTION_ID_COLUMN = "QUESTION_ID";

    // CONSTRAINT
    public static final int NAME_MAX_SIZE = 50;
    public static final int QUESTION_MIN_SIZE = 1;
    public static final int QUESTION_SENTENCE_MAX_SIZE = 500;
    public static final int OPTION_MAX_SIZE = 200;

    // MAPS
    public static final String QUIZ_MAPS_ID = "quizId";
    public static final String QUESTION_MAPS_ID = "questionId";

}
