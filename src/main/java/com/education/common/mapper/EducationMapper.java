package com.education.common.mapper;

import com.education.common.entity.EducationEntity;
import com.education.common.model.EducationModel;

import java.util.List;
import java.util.Set;

public interface EducationMapper<M extends EducationModel, E extends EducationEntity> {

    M convertEntityToModel(E entity);

    E convertModelToEntity(M model);

    List<M> convertEntityListToModelList(List<E> entityList);

    List<E> convertModelListToEntityList(List<M> modelList);

    Set<M> convertEntityListToModelList(Set<E> entitySet);

    Set<E> convertModelListToEntityList(Set<M> modelSet);

}
