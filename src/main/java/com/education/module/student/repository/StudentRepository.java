package com.education.module.student.repository;

import com.education.common.repository.EducationJpaRepository;
import com.education.module.student.entity.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends EducationJpaRepository<Student> {
}
