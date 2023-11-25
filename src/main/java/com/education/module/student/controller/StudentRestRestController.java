package com.education.module.student.controller;

import com.education.common.model.constraint.validation.EducationConstraintValidationGroups;
import com.education.module.student.model.StudentModel;
import com.education.module.student.model.StudentQuizAnswerModel;
import com.education.module.student.model.StudentQuizModel;
import com.education.module.student.model.StudentQuizResultModel;
import com.education.module.student.model.query.StudentQueryModel;
import com.education.module.student.model.view.StudentView;
import com.education.module.student.service.StudentService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
@RequestMapping(path = "/student")
@ApiResponses({
        @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
@AllArgsConstructor
public class StudentRestRestController implements StudentRestClient {

    private final StudentService studentService;

    @JsonView(StudentView.Query.class)
    @Operation(
            summary = "Save endpoint.",
            description = "The endpoint that enables addition")
    @Override
    public ResponseEntity<StudentModel> save(@Valid @NotNull @RequestBody
                                             @JsonView(StudentView.Command.class) StudentModel studentModel) {
        return ResponseEntity.ok(studentService.save(studentModel));
    }

    @JsonView(StudentView.Query.class)
    @Operation(
            summary = "Update endpoint.",
            description = "The endpoint that enables updating")
    @Override
    public ResponseEntity<StudentModel> update(@Validated(value = {EducationConstraintValidationGroups.ModifyingOperation.class})
                                               @NotNull @RequestBody
                                               @JsonView(StudentView.Command.class) StudentModel studentModel) {
        return ResponseEntity.ok(studentService.update(studentModel));
    }

    @Operation(
            summary = "Add quiz to student endpoint.",
            description = "The endpoint that enables adding quiz")
    @Override
    public ResponseEntity<Void> addQuiz(@Valid @NotNull @RequestBody StudentQuizModel studentQuizModel) {
        studentService.addQuiz(studentQuizModel);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Delete quiz to student endpoint.",
            description = "The endpoint that enables deleting quiz")
    @Override
    public ResponseEntity<Void> deleteQuiz(@Valid @NotNull @RequestBody StudentQuizModel studentQuizModel) {
        studentService.deleteQuiz(studentQuizModel);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Delete by id endpoint.",
            description = "The endpoint that enables subtraction")
    @Override
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") long id) {
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @JsonView(StudentView.Query.class)
    @Operation(
            summary = "Answer quiz.",
            description = "The endpoint that enables answer quiz and returns result.")
    @Override
    public ResponseEntity<Void> answerQuizQuestion(@Valid @NotNull @RequestBody
                                                   @JsonView(StudentView.Command.class) StudentQuizAnswerModel studentQuizAnswerModel) {
        studentService.answerQuizQuestion(studentQuizAnswerModel);
        return ResponseEntity.noContent().build();
    }

    @JsonView(StudentView.Query.class)
    @Operation(
            summary = "Get by id endpoint.",
            description = "The endpoint that enables returns object.")
    @Override
    public ResponseEntity<StudentModel> getById(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(studentService.getById(id));
    }

    @JsonView(StudentView.Query.class)
    @Operation(
            summary = "Query list endpoint.",
            description = "The endpoint that enables querying and returns a list of results.")
    @Override
    public ResponseEntity<List<StudentModel>> queryList(@Valid @NotNull @RequestBody StudentQueryModel studentQueryModel) {
        return ResponseEntity.ok(studentService.queryList(studentQueryModel));
    }

    @JsonView(StudentView.Query.class)
    @Operation(
            summary = "Query page endpoint.",
            description = "The endpoint that enables querying and returns a page of results.")
    @Override
    public ResponseEntity<Page<StudentModel>> queryPage(@Valid @NotNull @RequestBody StudentQueryModel studentQueryModel) {
        return ResponseEntity.ok(studentService.queryPage(studentQueryModel));
    }

    @Override
    public ResponseEntity<StudentQuizResultModel> getQuizResult(@PathVariable(value = "studentId") long studentId,
                                                                @PathVariable(value = "quizId") long quizId) {
        return ResponseEntity.ok(studentService.getQuizResult(studentId, quizId));
    }

}
