package com.education.module.student.controller;

import com.education.common.model.constraint.validation.EducationConstraintValidationGroups;
import com.education.module.student.model.StudentModel;
import com.education.module.student.model.StudentQuizAnswerModel;
import com.education.module.student.model.StudentQuizModel;
import com.education.module.student.model.StudentQuizResultModel;
import com.education.module.student.model.query.StudentQueryModel;
import com.education.module.student.model.view.StudentView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
public interface StudentRestClient {

    @JsonView(StudentView.Query.class)
    @PostMapping
    ResponseEntity<StudentModel> save(@Valid @NotNull @RequestBody
                                      @JsonView(StudentView.Command.class) StudentModel studentModel);

    @JsonView(StudentView.Query.class)
    @Validated(value = {Default.class, EducationConstraintValidationGroups.ModifyingOperation.class})
    @PutMapping
    ResponseEntity<StudentModel> update(@Valid @NotNull @RequestBody
                                        @JsonView(StudentView.Command.class) StudentModel studentModel);

    @PostMapping(path = "/quiz")
    ResponseEntity<Void> addQuiz(@Valid @NotNull @RequestBody StudentQuizModel studentQuizModel);

    @DeleteMapping(path = "/quiz")
    ResponseEntity<Void> deleteQuiz(@Valid @NotNull @RequestBody StudentQuizModel studentQuizModel);

    @DeleteMapping(path = "/by-id/{id}")
    ResponseEntity<Void> deleteById(@PathVariable(value = "id") long id);

    @JsonView(StudentView.Query.class)
    @PostMapping(path = "/answer-quiz")
    ResponseEntity<Void> answerQuizQuestion(@Valid @NotNull @RequestBody
                                            @JsonView(StudentView.Command.class) StudentQuizAnswerModel studentQuizAnswerModel);

    @JsonView(StudentView.Query.class)
    @GetMapping(path = "/by-id/{id}")
    ResponseEntity<StudentModel> getById(@PathVariable(value = "id") long id);

    @JsonView(StudentView.Query.class)
    @PostMapping(path = "/list")
    ResponseEntity<List<StudentModel>> queryList(@Valid @NotNull @RequestBody StudentQueryModel studentQueryModel);

    @JsonView(StudentView.Query.class)
    @PostMapping(path = "/page")
    ResponseEntity<Page<StudentModel>> queryPage(@Valid @NotNull @RequestBody StudentQueryModel studentQueryModel);

    @JsonView(StudentView.Query.class)
    @PostMapping(path = "/result-quiz/{studentId}/{quizId}")
    ResponseEntity<StudentQuizResultModel> getQuizResult(@PathVariable(value = "studentId") long studentId,
                                                         @PathVariable(value = "quizId") long quizId);

}
