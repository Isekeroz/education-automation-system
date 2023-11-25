package com.education.module.quiz.repository;

import com.education.common.repository.EducationJpaRepository;
import com.education.module.quiz.entity.Question;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends EducationJpaRepository<Question> {
}
