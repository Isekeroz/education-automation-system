package com.education.module.student.mapper;

import com.education.common.mapper.EducationMapper;
import com.education.module.student.entity.Student;
import com.education.module.student.model.StudentModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper extends EducationMapper<StudentModel, Student> {
}
