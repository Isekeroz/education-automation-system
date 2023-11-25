package com.education.common.repository;

import com.education.common.entity.EducationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EducationJpaRepository<E extends EducationEntity>
        extends JpaRepository<E, Long>, EducationJpaQuerydslRepository<E> {
}
