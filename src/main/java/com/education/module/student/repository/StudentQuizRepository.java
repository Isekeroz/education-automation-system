package com.education.module.student.repository;

import com.education.module.student.entity.StudentQuiz;
import com.education.module.student.entity.StudentQuizEmbeddable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentQuizRepository extends JpaRepository<StudentQuiz, StudentQuizEmbeddable>, QuerydslPredicateExecutor<StudentQuiz> {

    void deleteByIdStudentIdAndIdQuizId(Long studentId, Long quizId);

    StudentQuiz getAllByIdStudentIdAndIdQuizId(Long studentId, Long quizId);

}
