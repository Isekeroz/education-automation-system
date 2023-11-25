package com.education.module.student.service;

import com.education.common.model.constraint.validation.EducationConstraintValidationGroups;
import com.education.module.student.model.StudentModel;
import com.education.module.student.model.StudentQuizAnswerModel;
import com.education.module.student.model.StudentQuizModel;
import com.education.module.student.model.StudentQuizResultModel;
import com.education.module.student.model.query.StudentQueryModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface StudentService {

    StudentModel save(@Valid @NotNull StudentModel studentModel);

    StudentModel update(@Validated(value = {EducationConstraintValidationGroups.ModifyingOperation.class})
                        @NotNull StudentModel studentModel);

    void addQuiz(@Valid @NotNull StudentQuizModel studentQuizModel);

    void deleteQuiz(@Valid @NotNull StudentQuizModel studentQuizModel);

    void deleteById(long id);

    void answerQuizQuestion(@Valid @NotNull StudentQuizAnswerModel studentQuizAnswerModel);

    StudentModel getById(long id);

    List<StudentModel> queryList(@Valid @NotNull StudentQueryModel studentQueryModel);

    Page<StudentModel> queryPage(@Valid @NotNull StudentQueryModel studentQueryModel);

    StudentQuizResultModel getQuizResult(long studentId, long quizId);

}
