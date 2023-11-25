package com.education.server;

import com.education.server.configuration.EducationApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {EducationApplicationConfiguration.class})
public class EducationApplicationServer {

    public static void main(String[] args) {
        SpringApplication.run(EducationApplicationServer.class, args);
    }

}
