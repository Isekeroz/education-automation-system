package com.education.module.quiz.controller;

import com.education.common.model.constraint.validation.EducationConstraintValidationGroups;
import com.education.module.quiz.model.QuizModel;
import com.education.module.quiz.model.QuizQuestionModel;
import com.education.module.quiz.model.query.QuizQueryModel;
import com.education.module.quiz.model.view.QuizView;
import com.education.module.quiz.service.QuizService;
import com.fasterxml.jackson.annotation.JsonView;
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

import java.util.List;

@RestController
@RequestMapping(path = "/quiz")
@ApiResponses({
        @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
@AllArgsConstructor
public class QuizRestRestController implements QuizRestClient {

    private final QuizService quizService;

    @JsonView(QuizView.Query.class)
    @Operation(
            summary = "Save endpoint.",
            description = "The endpoint that enables addition")
    @Override
    public ResponseEntity<QuizModel> save(@Valid @NotNull @RequestBody
                                          @JsonView(QuizView.Command.class) QuizModel quizModel) {
        return ResponseEntity.ok(quizService.save(quizModel));
    }

    @JsonView(QuizView.Query.class)
    @Operation(
            summary = "Update endpoint.",
            description = "The endpoint that enables updating")
    @Validated(value = {Default.class, EducationConstraintValidationGroups.ModifyingOperation.class})
    @Override
    public ResponseEntity<QuizModel> update(@Valid @NotNull @RequestBody
                                            @JsonView(QuizView.Command.class) QuizModel quizModel) {
        return ResponseEntity.ok(quizService.update(quizModel));
    }

    @Operation(
            summary = "Add question to quiz endpoint.",
            description = "The endpoint that enables adding question")
    @Override
    public ResponseEntity<Void> addQuestion(@Valid @NotNull @RequestBody QuizQuestionModel quizQuestionModel) {
        quizService.addQuestion(quizQuestionModel);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Delete question to quiz endpoint.",
            description = "The endpoint that enables deleting question")
    @Override
    public ResponseEntity<Void> deleteQuestion(@Valid @NotNull @RequestBody QuizQuestionModel quizQuestionModel) {
        quizService.deleteQuestion(quizQuestionModel);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Delete by id endpoint.",
            description = "The endpoint that enables subtraction")
    @Override
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") long id) {
        quizService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @JsonView(QuizView.Query.class)
    @Operation(
            summary = "Get by id endpoint.",
            description = "The endpoint that enables returns object.")
    @Override
    public ResponseEntity<QuizModel> getById(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(quizService.getById(id));
    }

    @JsonView(QuizView.Query.class)
    @Operation(
            summary = "Query list endpoint.",
            description = "The endpoint that enables querying and returns a list of results.")
    @Override
    public ResponseEntity<List<QuizModel>> queryList(@Valid @NotNull @RequestBody QuizQueryModel quizQueryModel) {
        return ResponseEntity.ok(quizService.queryList(quizQueryModel));
    }

    @JsonView(QuizView.Query.class)
    @Operation(
            summary = "Query page endpoint.",
            description = "The endpoint that enables querying and returns a page of results.")
    @Override
    public ResponseEntity<Page<QuizModel>> queryPage(@Valid @NotNull @RequestBody QuizQueryModel quizQueryModel) {
        return ResponseEntity.ok(quizService.queryPage(quizQueryModel));
    }

}
