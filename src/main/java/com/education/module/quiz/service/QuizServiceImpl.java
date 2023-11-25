package com.education.module.quiz.service;

import com.education.common.model.constraint.validation.EducationConstraintValidationGroups;
import com.education.module.quiz.entity.QQuiz;
import com.education.module.quiz.entity.Quiz;
import com.education.module.quiz.entity.QuizQuestion;
import com.education.module.quiz.entity.QuizQuestionEmbeddable;
import com.education.module.quiz.generator.QuizQueryGeneratorImpl;
import com.education.module.quiz.mapper.QuizMapper;
import com.education.module.quiz.model.QuizModel;
import com.education.module.quiz.model.QuizQuestionModel;
import com.education.module.quiz.model.query.QuizQueryModel;
import com.education.module.quiz.repository.QuizQuestionRepository;
import com.education.module.quiz.repository.QuizRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class QuizServiceImpl implements QuizService {

    private static final String QUIZ_CACHE_NAME = "quiz";

    private static final String QUIZ_QUESTION_CACHE_NAME = "quiz_quest_count";

    private final QuizRepository quizRepository;

    private final QuizQuestionRepository quizQuestionRepository;

    private final QuizMapper quizMapper;

    private final QuizQueryGeneratorImpl quizQueryGenerator;

    @Override
    public QuizModel save(@Valid @NotNull QuizModel quizModel) {
        return quizMapper.convertEntityToModel(
                quizRepository.save(
                        quizMapper.convertModelToEntity(quizModel)
                )
        );
    }

    @CacheEvict(value = {QUIZ_CACHE_NAME, QUIZ_QUESTION_CACHE_NAME}, allEntries = true)
    @Override
    public QuizModel update(@Validated(value = {EducationConstraintValidationGroups.ModifyingOperation.class})
                            @NotNull QuizModel quizModel) {
        return save(quizModel);
    }

    @CacheEvict(value = {QUIZ_CACHE_NAME, QUIZ_QUESTION_CACHE_NAME}, allEntries = true)
    @Override
    public void addQuestion(@Valid @NotNull QuizQuestionModel quizQuestionModel) {
        for (final Long questionId : quizQuestionModel.getQuestionIds()) {
            quizQuestionRepository.save(
                    QuizQuestion.builder()
                            .id(QuizQuestionEmbeddable.builder()
                                    .quizId(quizQuestionModel.getQuizId())
                                    .questionId(questionId)
                                    .build())
                            .build());
        }
    }

    @CacheEvict(value = {QUIZ_CACHE_NAME, QUIZ_QUESTION_CACHE_NAME}, allEntries = true)
    @Override
    public void deleteQuestion(@Valid @NotNull QuizQuestionModel quizQuestionModel) {
        final Long quizId = quizQuestionModel.getQuizId();
        for (final Long questionId : quizQuestionModel.getQuestionIds()) {
            quizQuestionRepository.deleteByIdQuizIdAndIdQuestionId(quizId, questionId);
        }

        if (getQuestionCountById(quizId) <= 0) {
            deactiveQuiz(quizId);
        }
    }

    @CacheEvict(value = {QUIZ_CACHE_NAME, QUIZ_QUESTION_CACHE_NAME}, allEntries = true)
    @Override
    public void deleteById(long id) {
        quizRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = QUIZ_CACHE_NAME, key = "#id")
    @Override
    public QuizModel getById(long id) {
        return quizRepository.findById(id).map(quizMapper::convertEntityToModel).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<QuizModel> queryList(@Valid @NotNull QuizQueryModel quizQueryModel) {
        return quizMapper.convertEntityListToModelList(
                IterableUtils.toList(
                        quizRepository.findAll(
                                quizQueryGenerator.generate(
                                        quizQueryModel,
                                        QQuiz.quiz)
                        )
                )
        );
    }

    @Transactional(readOnly = true)
    @Override
    public Page<QuizModel> queryPage(@Valid @NotNull QuizQueryModel quizQueryModel) {
        return quizRepository.findAll(
                quizQueryGenerator.generate(
                        quizQueryModel,
                        QQuiz.quiz),
                quizQueryModel.getPageable()
        ).map(quizMapper::convertEntityToModel);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = QUIZ_QUESTION_CACHE_NAME, key = "#quizId")
    @Override
    public int getQuestionCountById(long quizId) {
        return quizQuestionRepository.countByIdQuizId(quizId);
    }

    private void deactiveQuiz(Long quizId) {
        final Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        if (quizOptional.isPresent()) {
            final Quiz quiz = quizOptional.get();
            quiz.setActive(false);
            quizRepository.save(quiz);
        }
    }

}
