package com.pafassigment.fitplus;

import com.pafassigment.fitplus.configuration.properties.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class FitPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(FitPlusApplication.class, args);
    }

}
