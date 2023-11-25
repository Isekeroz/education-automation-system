package com.education.module.student.configuration;

import com.education.module.student.configuration.constant.StudentConfigurationConstant;
import com.education.module.student.entity.Student;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {StudentConfigurationConstant.BASE_PACKAGE})
@EntityScan(basePackageClasses = {Student.class})
@EnableJpaRepositories(basePackages = {StudentConfigurationConstant.REPOSITORY_PACKAGE})
public class StudentServiceConfiguration {
}
