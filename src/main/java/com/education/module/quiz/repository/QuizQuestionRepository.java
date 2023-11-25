package com.education.module.quiz.repository;

import com.education.module.quiz.entity.QuizQuestion;
import com.education.module.quiz.entity.QuizQuestionEmbeddable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizQuestionRepository
        extends JpaRepository<QuizQuestion, QuizQuestionEmbeddable>, QuerydslPredicateExecutor<QuizQuestion> {

    void deleteByIdQuizIdAndIdQuestionId(Long quizId, Long questionId);

    int countByIdQuizId(Long quizId);

}
