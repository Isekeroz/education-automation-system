package com.education.module.student.service;

import com.education.common.model.constraint.validation.EducationConstraintValidationGroups;
import com.education.module.quiz.model.QuizModel;
import com.education.module.quiz.service.QuizService;
import com.education.module.student.entity.*;
import com.education.module.student.exception.StudentQuizCompletedException;
import com.education.module.student.exception.StudentQuizNotFoundException;
import com.education.module.student.exception.StudentQuizQuestionNotFoundException;
import com.education.module.student.generator.StudentQueryGeneratorImpl;
import com.education.module.student.mapper.StudentMapper;
import com.education.module.student.model.*;
import com.education.module.student.model.query.StudentQueryModel;
import com.education.module.student.repository.StudentQuizAnswerRepository;
import com.education.module.student.repository.StudentQuizRepository;
import com.education.module.student.repository.StudentRepository;
import com.querydsl.core.BooleanBuilder;
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
import java.util.Objects;

@Service
@AllArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {

    private static final String STUDENT_CACHE_NAME = "student";

    private static final String QUIZ_RESULT_CACHE_NAME = "quiz_result";

    private final StudentRepository studentRepository;

    private final StudentQuizRepository studentQuizRepository;

    private final StudentQuizAnswerRepository studentQuizAnswerRepository;

    private final StudentMapper studentMapper;

    private final StudentQueryGeneratorImpl studentQueryGenerator;

    private final QuizService quizService;

    @Override
    public StudentModel save(@Valid @NotNull StudentModel studentModel) {
        return studentMapper.convertEntityToModel(
                studentRepository.save(
                        studentMapper.convertModelToEntity(studentModel)
                )
        );
    }

    @CacheEvict(value = {STUDENT_CACHE_NAME}, allEntries = true)
    @Override
    public StudentModel update(@Validated(value = {EducationConstraintValidationGroups.ModifyingOperation.class})
                               @NotNull StudentModel studentModel) {
        return save(studentModel);
    }

    @CacheEvict(value = {STUDENT_CACHE_NAME}, allEntries = true)
    @Override
    public void addQuiz(@Valid @NotNull StudentQuizModel studentQuizModel) {
        for (final Long quizId : studentQuizModel.getQuizIds()) {
            final QuizModel quizModel = quizService.getById(quizId);
            if (Objects.nonNull(quizModel) && quizModel.isActive()) {
                studentQuizRepository.save(
                        StudentQuiz.builder()
                                .id(StudentQuizEmbeddable.builder()
                                        .studentId(studentQuizModel.getStudentNumber())
                                        .quizId(quizId)
                                        .build())
                                .build());
            }
        }
    }

    @CacheEvict(value = {STUDENT_CACHE_NAME}, allEntries = true)
    @Override
    public void deleteQuiz(@Valid @NotNull StudentQuizModel studentQuizModel) {
        for (final Long quizId : studentQuizModel.getQuizIds()) {
            studentQuizRepository.deleteByIdStudentIdAndIdQuizId(studentQuizModel.getStudentNumber(), quizId);
        }
    }

    @Override
    public void answerQuizQuestion(@Valid @NotNull StudentQuizAnswerModel studentQuizAnswerModel) {
        final Long studentId = studentQuizAnswerModel.getStudentId();
        final Long quizId = studentQuizAnswerModel.getQuizId();
        final StudentQuiz studentQuiz = studentQuizRepository.getAllByIdStudentIdAndIdQuizId(studentId, quizId);
        if (Objects.isNull(studentQuiz)) {
            throw new StudentQuizNotFoundException(quizId);
        }

        if (studentQuiz.isCompleted()) {
            throw new StudentQuizCompletedException(studentId, quizId);
        }

        final QuizModel quizModel = quizService.getById(quizId);
        if (Objects.isNull(quizModel)) {
            throw new StudentQuizNotFoundException(quizId);
        }

        for (final StudentQuestionAnswerModel studentQuestionAnswerModel : studentQuizAnswerModel.getQuestionAnswers()) {
            final Long questionId = studentQuestionAnswerModel.getQuestionId();
            boolean hasQuestion = quizModel.getQuestionIds().contains(questionId);
            if (!hasQuestion) {
                throw new StudentQuizQuestionNotFoundException(quizId, questionId);
            }

            studentQuizAnswerRepository.save(
                    StudentQuizAnswer.builder()
                            .studentId(studentId)
                            .quizId(quizId)
                            .questionId(questionId)
                            .answer(studentQuestionAnswerModel.getAnswer())
                            .build()
            );

            studentQuiz.setCompleted(true);
            studentQuizRepository.save(studentQuiz);
        }
    }

    @CacheEvict(value = {STUDENT_CACHE_NAME}, allEntries = true)
    @Override
    public void deleteById(long id) {
        studentRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = STUDENT_CACHE_NAME, key = "#id")
    @Override
    public StudentModel getById(long id) {
        return studentRepository.findById(id).map(studentMapper::convertEntityToModel).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<StudentModel> queryList(@Valid @NotNull StudentQueryModel studentQueryModel) {
        return studentMapper.convertEntityListToModelList(
                IterableUtils.toList(
                        studentRepository.findAll(
                                studentQueryGenerator.generate(
                                        studentQueryModel,
                                        QStudent.student)
                        )
                )
        );
    }

    @Transactional(readOnly = true)
    @Override
    public Page<StudentModel> queryPage(@Valid @NotNull StudentQueryModel studentQueryModel) {
        return studentRepository.findAll(
                studentQueryGenerator.generate(
                        studentQueryModel,
                        QStudent.student),
                studentQueryModel.getPageable()
        ).map(studentMapper::convertEntityToModel);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = QUIZ_RESULT_CACHE_NAME, key = "#studentId + '_' + #quizId")
    @Override
    public StudentQuizResultModel getQuizResult(long studentId, long quizId) {
        int answeredQuestionCount = 0;
        int correctAnswersCount = 0;
        for (final StudentQuizAnswer studentQuizAnswer : studentQuizAnswerRepository.findAll(prepareExpression(studentId, quizId))) {
            if (Objects.equals(studentQuizAnswer.getQuestion().getCorrectOption(), studentQuizAnswer.getAnswer())) {
                correctAnswersCount++;
            }
            answeredQuestionCount++;
        }
        return StudentQuizResultModel.builder()
                .questionCount(quizService.getQuestionCountById(quizId))
                .answeredQuestionCount(answeredQuestionCount)
                .correctAnswersCount(correctAnswersCount)
                .incorrectAnswersCount(answeredQuestionCount - correctAnswersCount)
                .build();
    }

    private BooleanBuilder prepareExpression(long studentId, long quizId) {
        final BooleanBuilder expressionBuilder = new BooleanBuilder();
        expressionBuilder.and(QStudentQuizAnswer.studentQuizAnswer.studentId.eq(studentId))
                .and(QStudentQuizAnswer.studentQuizAnswer.quizId.eq(quizId));
        return expressionBuilder;
    }

}
