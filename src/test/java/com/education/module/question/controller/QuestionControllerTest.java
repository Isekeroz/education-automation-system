package com.education.module.question.controller;

import com.education.EducationBaseTest;
import com.education.common.data.Answer;
import com.education.common.model.EducationPageable;
import com.education.module.quiz.model.QuestionModel;
import com.education.module.quiz.model.query.QuestionQueryModel;
import com.education.module.quiz.service.QuestionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("QuestionControllerUnitTest")
@DisplayName("Question Controller Unit Tests")
@Sql(scripts = "classpath:db/student/populateDatabase.sql")
public class QuestionControllerTest extends EducationBaseTest {

    private static final String CONTEXT_REQUEST_PATH = "/question";

    private static final String DEFAULT_QUESTION_SENTENCE = "Example Question?";

    private static final String DEFAULT_FIRST_OPTION = "option1";

    private static final String DEFAULT_SECOND_OPTION = "option2";

    private static final String DEFAULT_THIRD_OPTION = "option3";

    private static final Answer DEFAULT_CORRECT_OPTION = Answer.FIRST_OPTION;

    private static final long DEFAULT_QUESTION_ID = 100L;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private QuestionService questionService;

    @Test
    @DisplayName("Given valid question data, when save new question, then question is returned")
    void givenValidQuestionData_whenSaveQuestion_ThenQuestionReturned() throws Exception {
        mvc.perform(
                post(CONTEXT_REQUEST_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(QuestionModel.builder()
                                .questionSentence(DEFAULT_QUESTION_SENTENCE)
                                .firstOption(DEFAULT_FIRST_OPTION)
                                .secondOption(DEFAULT_SECOND_OPTION)
                                .thirdOption(DEFAULT_THIRD_OPTION)
                                .correctOption(DEFAULT_CORRECT_OPTION)
                                .build())))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Given invalid question data, when save new question, then failed")
    void givenInvalidQuestionData_whenSaveQuestion_ThenFailed() throws Exception {
        mvc.perform(
                post(CONTEXT_REQUEST_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(QuestionModel.builder()
                                .questionSentence(DEFAULT_QUESTION_SENTENCE)
                                .secondOption(DEFAULT_SECOND_OPTION)
                                .thirdOption(DEFAULT_THIRD_OPTION)
                                .correctOption(DEFAULT_CORRECT_OPTION)
                                .build())))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("Given valid question data, when update question, then updated question is returned")
    void givenValidQuestionData_whenUpdateQuestion_ThenQuestionReturned() throws Exception {
        final QuestionModel result = questionService.getById(DEFAULT_QUESTION_ID);
        result.setFirstOption(DEFAULT_SECOND_OPTION);

        mvc.perform(
                put(CONTEXT_REQUEST_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(result)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Given invalid question data, when update question, then failed")
    void givenInvalidStudentData_whenUpdateStudent_ThenFailed() throws Exception {
        final QuestionModel result = questionService.getById(DEFAULT_QUESTION_ID);
        result.setFirstOption(null);

        mvc.perform(
                put(CONTEXT_REQUEST_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(result)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("Given valid question id, when delete, then question is deleted")
    void givenValidQuestionId_whenDeleteQuestion_ThenSuccessReturned() throws Exception {
        mvc.perform(
                delete(CONTEXT_REQUEST_PATH + "/by-id/{id}", DEFAULT_QUESTION_ID))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Given valid question id, when retrieve question, then question is returned")
    void givenValidQuestionId_whenRetrieveQuestion_ThenQuestionReturned() throws Exception {
        mvc.perform(
                get(CONTEXT_REQUEST_PATH + "/by-id/{id}", DEFAULT_QUESTION_ID))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Given invalid question id, when retrieve question, then question is returned")
    void giveInvalidQuestionId_whenRetrieveQuestion_ThenNullReturn() throws Exception {
        final long invalidQuestionId = 300L;

        final MvcResult mvcResult = mvc.perform(
                get(CONTEXT_REQUEST_PATH + "/by-id/{id}", invalidQuestionId))
                .andExpect(status().isOk()).andReturn();

        assertTrue(StringUtils.isBlank(mvcResult.getResponse().getContentAsString()));
    }

    @Test
    @DisplayName("Given valid question query, when retrieve questions, then questions are returned")
    void givenValidQuestionQuery_whenRetrieveQuestions_ThenQuestionsReturned() throws Exception {
        mvc.perform(
                post(CONTEXT_REQUEST_PATH + "/page")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(QuestionQueryModel.builder()
                                .pageable(new EducationPageable(0, 5))
                                .build())))
                .andExpect(status().isOk());
    }

}
