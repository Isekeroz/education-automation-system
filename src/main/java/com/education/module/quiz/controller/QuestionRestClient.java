package com.education.module.quiz.controller;

import com.education.common.model.constraint.validation.EducationConstraintValidationGroups;
import com.education.module.quiz.model.QuestionModel;
import com.education.module.quiz.model.query.QuestionQueryModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
public interface QuestionRestClient {

    @PostMapping
    ResponseEntity<QuestionModel> save(@Valid @NotNull @RequestBody QuestionModel questionModel);

    @PutMapping
    @Validated(value = {Default.class, EducationConstraintValidationGroups.ModifyingOperation.class})
    ResponseEntity<QuestionModel> update(@Valid @NotNull @RequestBody QuestionModel questionModel);

    @DeleteMapping(path = "/by-id/{id}")
    ResponseEntity<Void> deleteById(@PathVariable(value = "id") long id);

    @GetMapping(path = "/by-id/{id}")
    ResponseEntity<QuestionModel> getById(@PathVariable(value = "id") long id);

    @PostMapping(value = "/page")
    ResponseEntity<Page<QuestionModel>> queryPage(@Valid @NotNull @RequestBody QuestionQueryModel questionQueryModel);
}
