package com.education.module.quiz.controller;

import com.education.common.model.constraint.validation.EducationConstraintValidationGroups;
import com.education.module.quiz.model.QuizModel;
import com.education.module.quiz.model.QuizQuestionModel;
import com.education.module.quiz.model.query.QuizQueryModel;
import com.education.module.quiz.model.view.QuizView;
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
public interface QuizRestClient {

    @JsonView(QuizView.Query.class)
    @PostMapping
    ResponseEntity<QuizModel> save(@Valid @NotNull @RequestBody
                                   @JsonView(QuizView.Command.class) QuizModel quizModel);

    @JsonView(QuizView.Query.class)
    @Validated(value = {Default.class, EducationConstraintValidationGroups.ModifyingOperation.class})
    @PutMapping
    ResponseEntity<QuizModel> update(@Valid @NotNull @RequestBody
                                     @JsonView(QuizView.Command.class) QuizModel quizModel);

    @PostMapping(path = "/question")
    ResponseEntity<Void> addQuestion(@Valid @NotNull @RequestBody QuizQuestionModel quizQuestionModel);

    @DeleteMapping(path = "/question")
    ResponseEntity<Void> deleteQuestion(@Valid @NotNull @RequestBody QuizQuestionModel quizQuestionModel);

    @DeleteMapping(path = "/by-id/{id}")
    ResponseEntity<Void> deleteById(@PathVariable(value = "id") long id);

    @JsonView(QuizView.Query.class)
    @GetMapping(path = "/by-id/{id}")
    ResponseEntity<QuizModel> getById(@PathVariable(value = "id") long id);

    @JsonView(QuizView.Query.class)
    @PostMapping(value = "/list")
    ResponseEntity<List<QuizModel>> queryList(@Valid @NotNull @RequestBody QuizQueryModel quizQueryModel);

    @JsonView(QuizView.Query.class)
    @PostMapping(value = "/page")
    ResponseEntity<Page<QuizModel>> queryPage(@Valid @NotNull @RequestBody QuizQueryModel quizQueryModel);

}
