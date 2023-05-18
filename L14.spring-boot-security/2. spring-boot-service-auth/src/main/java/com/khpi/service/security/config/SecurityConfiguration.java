package com.khpi.service.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/*
 *** 1. Demonstration of permitAll() vs authenticated() ***
 */

@Configuration
@ComponentScan("com.khpi")
@EnableWebSecurity
public class SecurityConfiguration
{
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http
            .authorizeHttpRequests()
            .requestMatchers("/css/**").permitAll()
           // .requestMatchers("/users**").permitAll();
            .requestMatchers("/users**").authenticated()

        .and()
        .formLogin();

        return http.build();
    }
}

