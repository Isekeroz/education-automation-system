package com.education.common.repository;

import com.education.common.entity.BaseEducationEntity;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface EducationJpaQuerydslRepository<E extends BaseEducationEntity<Long>>
        extends QuerydslPredicateExecutor<E> {

    Optional<E> findById(long id);

    <E extends BaseEducationEntity> E save(E entity);

}
