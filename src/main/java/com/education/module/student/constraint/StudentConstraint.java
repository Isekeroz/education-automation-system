package com.education.module.student.constraint;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StudentConstraint {

    // TABLE
    public static final String STUDENT_TABLE_NAME = "STUDENT";
    public static final String STUDENT_QUIZ_TABLE_NAME = "STUDENT_QUIZ";
    public static final String STUDENT_QUIZ_ANSWER_TABLE_NAME = "STUDENT_QUIZ_ANSWER";
    public static final String STUDENT_SEQUENCE_NAME = "STUDENT_SEQ";
    public static final String STUDENT_QUIZ_ANSWER_SEQUENCE_NAME = "STUDENT_QUIZ_ANSWER_SEQ";

    // COLUMN
    public static final String NAME_COLUMN = "NAME";
    public static final String SURNAME_COLUMN = "SURNAME";
    public static final String STUDENT_ID_COLUMN = "STUDENT_ID";
    public static final String QUIZ_ID_COLUMN = "QUIZ_ID";
    public static final String QUESTION_ID_COLUMN = "QUESTION_ID";
    public static final String ANSWER_COLUMN = "ANSWER";
    public static final String EMAIL_COLUMN = "EMAIL";
    public static final String COMPLETED_COLUMN = "IS_COMPLETED";

    // CONSTRAINT
    public static final int NAME_MAX_SIZE = 50;
    public static final int SURNAME_MAX_SIZE = 50;
    public static final int EMAIL_MAX_SIZE = 30;

    // MAPS
    public static final String STUDENT_MAPS_ID = "studentId";
    public static final String QUIZ_MAPS_ID = "quizId";
    public static final String QUESTION_MAPS_ID = "questionId";
}
