package com.education;

import com.education.configuration.EducationTestConfiguration;
import com.education.server.configuration.EducationApplicationConfiguration;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(classes = {EducationApplicationConfiguration.class, EducationTestConfiguration.class})
@AutoConfigureMockMvc
@EnableAutoConfiguration
@TestInstance(PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public abstract class EducationBaseTest {
}
