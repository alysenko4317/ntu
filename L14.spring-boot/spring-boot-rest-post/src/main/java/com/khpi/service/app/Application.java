package com.khpi.service.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages = "com.khpi.service")
@EnableJpaRepositories(basePackages = "com.khpi.service.repositories")
@EntityScan(basePackages = "com.khpi.service.models")
public class Application
{
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            public String encode(String password) {
                return password;
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
