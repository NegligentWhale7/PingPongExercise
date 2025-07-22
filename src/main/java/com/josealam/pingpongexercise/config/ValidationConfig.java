package com.josealam.pingpongexercise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import jakarta.validation.Validator;

/**
 * Configuration class for Jakarta Bean Validation
 * This ensures proper setup of Jakarta validation in the Spring Boot application
 */
@Configuration
public class ValidationConfig {

    /**
     * Creates a Jakarta Bean Validation validator factory bean
     * @return LocalValidatorFactoryBean configured for Jakarta validation
     */
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    /**
     * Provides a Jakarta Bean Validator instance
     * @return Validator instance for programmatic validation
     */
    @Bean
    public Validator jakartaValidator() {
        return validator().getValidator();
    }
}
