package com.education.module.quiz.service;

import com.education.common.model.constraint.validation.EducationConstraintValidationGroups;
import com.education.module.quiz.entity.QQuestion;
import com.education.module.quiz.generator.QuestionQueryGeneratorImpl;
import com.education.module.quiz.mapper.QuestionMapper;
import com.education.module.quiz.model.QuestionModel;
import com.education.module.quiz.model.query.QuestionQueryModel;
import com.education.module.quiz.repository.QuestionRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@AllArgsConstructor
@Transactional
public class QuestionServiceImpl implements QuestionService {

    private static final String QUESTION_CACHE_NAME = "question";

    private final QuestionRepository questionRepository;

    private final QuestionMapper questionMapper;

    private final QuestionQueryGeneratorImpl questionQueryGenerator;

    @Override
    public QuestionModel save(@Valid @NotNull QuestionModel questionModel) {
        return questionMapper.convertEntityToModel(
                questionRepository.save(
                        questionMapper.convertModelToEntity(questionModel)
                )
        );
    }

    @CacheEvict(value = {QUESTION_CACHE_NAME}, allEntries = true)
    @Validated(value = {Default.class, EducationConstraintValidationGroups.ModifyingOperation.class})
    @Override
    public QuestionModel update(@Valid @NotNull QuestionModel questionModel) {
        return save(questionModel);
    }

    @CacheEvict(value = {QUESTION_CACHE_NAME}, allEntries = true)
    @Override
    public void deleteById(long id) {
        questionRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = QUESTION_CACHE_NAME, key = "#id")
    @Override
    public QuestionModel getById(long id) {
        return questionRepository.findById(id).map(questionMapper::convertEntityToModel).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<QuestionModel> queryPage(@Valid @NotNull QuestionQueryModel questionQueryModel) {
        return questionRepository.findAll(
                questionQueryGenerator.generate(
                        questionQueryModel,
                        QQuestion.question),
                questionQueryModel.getPageable()
        ).map(questionMapper::convertEntityToModel);
    }

}
