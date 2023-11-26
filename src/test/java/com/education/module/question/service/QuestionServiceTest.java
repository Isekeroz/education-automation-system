package com.education.module.question.service;

import com.education.EducationBaseTest;
import com.education.common.data.Answer;
import com.education.common.model.EducationPageable;
import com.education.module.quiz.model.QuestionModel;
import com.education.module.quiz.model.query.QuestionQueryModel;
import com.education.module.quiz.service.QuestionService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@Tag("QuestionServiceUnitTest")
@DisplayName("Question Service Unit Tests")
@Sql(scripts = "classpath:db/student/populateDatabase.sql")
public class QuestionServiceTest extends EducationBaseTest {

    private static final String DEFAULT_QUESTION_SENTENCE = "Example Question?";

    private static final String DEFAULT_FIRST_OPTION = "option1";

    private static final String DEFAULT_SECOND_OPTION = "option2";

    private static final String DEFAULT_THIRD_OPTION = "option3";

    private static final Answer DEFAULT_CORRECT_OPTION = Answer.FIRST_OPTION;

    private static final long DEFAULT_QUESTION_ID = 100L;

    @Autowired
    private QuestionService questionService;

    @Test
    @DisplayName("Given valid question data, when save new question, then question is returned")
    void givenValidQuestionData_whenSaveQuestion_ThenQuestionReturned() {
        final QuestionModel result = questionService.save(QuestionModel.builder()
                .questionSentence(DEFAULT_QUESTION_SENTENCE)
                .firstOption(DEFAULT_FIRST_OPTION)
                .secondOption(DEFAULT_SECOND_OPTION)
                .thirdOption(DEFAULT_THIRD_OPTION)
                .correctOption(DEFAULT_CORRECT_OPTION)
                .build());

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getVersion());
        assertNotNull(result.getCreatedBy());
        assertNotNull(result.getUpdatedBy());
        assertEquals(DEFAULT_QUESTION_SENTENCE, result.getQuestionSentence());
        assertEquals(DEFAULT_FIRST_OPTION, result.getFirstOption());
        assertEquals(DEFAULT_SECOND_OPTION, result.getSecondOption());
        assertEquals(DEFAULT_THIRD_OPTION, result.getThirdOption());
        assertEquals(DEFAULT_CORRECT_OPTION, result.getCorrectOption());
    }

    @Test
    @DisplayName("Given invalid question data, when save new question, then failed")
    void givenInvalidQuestionData_whenSaveQuestion_ThenFailed() {
        final Exception exception = assertThrows(ConstraintViolationException.class,
                () -> questionService.save(QuestionModel.builder()
                        .questionSentence(DEFAULT_QUESTION_SENTENCE)
                        .secondOption(DEFAULT_SECOND_OPTION)
                        .thirdOption(DEFAULT_THIRD_OPTION)
                        .correctOption(DEFAULT_CORRECT_OPTION)
                        .build()));

        final String expectedErrorMessage = "save.questionModel.firstOption: must not be blank";
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Given valid question data, when update question, then updated question is returned")
    void givenValidQuestionData_whenUpdateQuestion_ThenQuestionReturned() {
        final long expectedId = 100L;
        final String expectedQuestionSentence = "Example Question?";
        final String expectedSecondOption = "Second Option";
        final String expectedThirdOption = "Third Option";
        final Answer expectedCorrectOption = Answer.FIRST_OPTION;

        QuestionModel result = questionService.getById(expectedId);

        final String expectedFirstOption = "Updated first option";
        result.setFirstOption(expectedFirstOption);
        result = questionService.update(result);

        assertNotNull(result);
        assertEquals(expectedId, result.getId());
        assertNotNull(result.getVersion());
        assertNotNull(result.getCreatedBy());
        assertNotNull(result.getUpdatedBy());
        assertEquals(expectedQuestionSentence, result.getQuestionSentence());
        assertEquals(expectedFirstOption, result.getFirstOption());
        assertEquals(expectedSecondOption, result.getSecondOption());
        assertEquals(expectedThirdOption, result.getThirdOption());
        assertEquals(expectedCorrectOption, result.getCorrectOption());
    }

    @Test
    @DisplayName("Given invalid question data, when update question, then failed")
    void givenInvalidStudentData_whenUpdateStudent_ThenFailed() {
        final QuestionModel result = questionService.getById(100L);

        result.setSecondOption(null);

        final Exception exception = assertThrows(ConstraintViolationException.class,
                () -> questionService.update(result));

        final String expectedErrorMessage = "update.questionModel.secondOption: must not be blank";
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Given valid question id, when delete, then question is deleted")
    void givenValidQuestionId_whenDeleteQuestion_ThenSuccessReturned() {
        QuestionModel question = questionService.getById(DEFAULT_QUESTION_ID);
        assertNotNull(question);

        questionService.deleteById(DEFAULT_QUESTION_ID);

        QuestionModel result = questionService.getById(DEFAULT_QUESTION_ID);
        assertNull(result);
    }

    @Test
    @DisplayName("Given valid question id, when retrieve question, then question is returned")
    void givenValidQuestionId_whenRetrieveQuestion_ThenQuestionReturned() {
        final QuestionModel result = questionService.getById(DEFAULT_QUESTION_ID);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getVersion());
        assertNotNull(result.getCreatedBy());
        assertNotNull(result.getUpdatedBy());
        assertEquals(DEFAULT_QUESTION_ID, result.getId());
    }

    @Test
    @DisplayName("Given invalid question id, when retrieve question, then question is returned")
    void giveInvalidQuestionId_whenRetrieveQuestion_ThenNullReturn() {
        final long invalidQuestionId = 300L;

        final QuestionModel result = questionService.getById(invalidQuestionId);

        assertNull(result);
    }

    @Test
    @DisplayName("Given valid question query, when retrieve questions, then questions are returned")
    void givenValidQuestionQuery_whenRetrieveQuestions_ThenQuestionsReturned() {
        final long expectedTotalElements = 2;
        final long expectedTotalPages = 1;

        final QuestionQueryModel questionQueryModel = QuestionQueryModel.builder()
                .pageable(new EducationPageable(0, 5))
                .build();

        final Page<QuestionModel> results = questionService.queryPage(questionQueryModel);

        assertNotNull(results);
        assertEquals(expectedTotalElements, results.getTotalElements());
        assertEquals(expectedTotalPages, results.getTotalPages());
    }

    @Test
    @DisplayName("Given invalid question query, when retrieve questions, then failed")
    void givenInvalidQuestionQuery_whenRetrieveQuestions_ThenFailed() {
        final Exception exception = assertThrows(IllegalArgumentException.class,
                () -> QuestionQueryModel.builder()
                        .pageable(new EducationPageable(-1, 5))
                        .build());

        final String expectedErrorMessage = "Page index must not be less than zero";
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

}
