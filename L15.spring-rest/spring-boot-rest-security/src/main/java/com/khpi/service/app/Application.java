package com.khpi.service.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@SpringBootApplication
@ComponentScan(basePackages = "com.khpi.service")
@EnableJpaRepositories(basePackages = "com.khpi.service.repositories")
@EntityScan(basePackages = "com.khpi.service.models")
public class Application //extends WebMvcConfigurerAdapter
{
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
