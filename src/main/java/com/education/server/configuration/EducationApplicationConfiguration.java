package com.education.server.configuration;

import com.education.module.quiz.configuration.QuizServiceConfiguration;
import com.education.module.student.configuration.StudentServiceConfiguration;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Caffeine;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@Import(value = {QuizServiceConfiguration.class, StudentServiceConfiguration.class})
@EnableCaching
@AllArgsConstructor
public class EducationApplicationConfiguration implements InitializingBean {

    private final ApplicationContext applicationContext;

    private static Info createInfo() {
        return new Info()
                .title("Education Automation System")
                .version("1.0.0")
                .contact(createContact())
                .description("Education Automation System")
                .termsOfService("https://education-automation-system.com")
                .license(createLicense());
    }

    private static Contact createContact() {
        final Contact contact = new Contact();
        contact.setEmail("info@education.com");
        contact.setName("Education Administrator");
        contact.setUrl("https://education-automation-system.com");
        return contact;
    }

    private static License createLicense() {
        return new License()
                .name("MIT License")
                .url("https://choosealicense.com/licenses/mit/");
    }

    private static List<Server> createServer() {
        final Server server = new Server();
        server.setUrl("http://localhost" + ":2323");
        server.setDescription("Education Automation System Application");
        return List.of(server);
    }

    @Override
    public void afterPropertiesSet() {
        final ObjectMapper objectMapper = applicationContext.getBean(ObjectMapper.class);
        objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Bean
    public Caffeine<Object, Object> caffeineConfig() {
        return Caffeine.newBuilder().expireAfterWrite(60, TimeUnit.MINUTES);
    }

    @Bean
    public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
        final CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }

    @Bean
    OpenAPI openApi() {
        return new OpenAPI()
                .info(createInfo())
                .servers(createServer());
    }

}
