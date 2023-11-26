package com.education.module.quiz.controller;

import com.education.common.model.constraint.validation.EducationConstraintValidationGroups;
import com.education.module.quiz.model.QuestionModel;
import com.education.module.quiz.model.query.QuestionQueryModel;
import com.education.module.quiz.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/question")
@ApiResponses({
        @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
@AllArgsConstructor
public class QuestionRestRestController implements QuestionRestClient {

    private final QuestionService questionService;

    @Operation(
            summary = "Save endpoint.",
            description = "The endpoint that enables addition")
    @Override
    public ResponseEntity<QuestionModel> save(@Valid @NotNull @RequestBody QuestionModel questionModel) {
        return ResponseEntity.ok(questionService.save(questionModel));
    }

    @Operation(
            summary = "Update endpoint.",
            description = "The endpoint that enables updating")
    @Validated(value = {Default.class, EducationConstraintValidationGroups.ModifyingOperation.class})
    @Override
    public ResponseEntity<QuestionModel> update(@Valid @NotNull @RequestBody QuestionModel questionModel) {
        return ResponseEntity.ok(questionService.update(questionModel));
    }

    @Operation(
            summary = "Delete by id endpoint.",
            description = "The endpoint that enables subtraction")
    @Override
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") long id) {
        questionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Get by id endpoint.",
            description = "The endpoint that enables returns object.")
    @Override
    public ResponseEntity<QuestionModel> getById(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(questionService.getById(id));
    }

    @Operation(
            summary = "Query page endpoint.",
            description = "The endpoint that enables querying and returns a page of results.")
    @Override
    public ResponseEntity<Page<QuestionModel>> queryPage(@Valid @NotNull @RequestBody QuestionQueryModel questionQueryModel) {
        return ResponseEntity.ok(questionService.queryPage(questionQueryModel));
    }

}
