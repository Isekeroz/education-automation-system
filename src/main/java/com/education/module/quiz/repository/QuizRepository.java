package com.education.module.quiz.repository;

import com.education.common.repository.EducationJpaRepository;
import com.education.module.quiz.entity.Quiz;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends EducationJpaRepository<Quiz> {
}
