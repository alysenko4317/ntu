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

//@Configuration
//@ComponentScan("com.khpi")
//@EnableWebSecurity
public class SecurityConfiguration0
{
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http
            .authorizeHttpRequests()
            .requestMatchers("/css/**").permitAll()
            .requestMatchers("/users**").permitAll();
           // .requestMatchers("/users**").authenticated();

        return http.build();
    }
}

/*
@Configuration
@ComponentScan("com.khpi")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    // for older Spring versions the implementation of the class was different:
    // https://stackoverflow.com/questions/74609057/how-to-fix-spring-authorizerequests-is-deprecated
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/users/**").authenticated();
    }
}*/
