package com.education.module.student.repository;

import com.education.common.repository.EducationJpaRepository;
import com.education.module.student.entity.StudentQuizAnswer;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentQuizAnswerRepository extends EducationJpaRepository<StudentQuizAnswer> {
}
