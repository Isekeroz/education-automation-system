package com.education.module.quiz.configuration;

import com.education.module.quiz.configuration.constant.QuizConfigurationConstant;
import com.education.module.quiz.entity.Quiz;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {QuizConfigurationConstant.BASE_PACKAGE})
@EntityScan(basePackageClasses = {Quiz.class})
@EnableJpaRepositories(basePackages = {QuizConfigurationConstant.REPOSITORY_PACKAGE})
public class QuizServiceConfiguration {
}
