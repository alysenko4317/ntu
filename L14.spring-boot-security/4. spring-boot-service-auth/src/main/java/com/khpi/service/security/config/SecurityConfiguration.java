package com.khpi.service.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
 *** 2. Demonstration of inMemoryAuthentication ***
 */

@Configuration
@ComponentScan("com.khpi")
@EnableWebSecurity
public class SecurityConfiguration
{
    private final PasswordEncoder passwordEncoder;

    @Autowired
    SecurityConfiguration(PasswordEncoder pe) {
        passwordEncoder = pe;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http
            .csrf().disable()

            .authorizeHttpRequests()
                .requestMatchers("/users**").authenticated()
                .requestMatchers("/css/**").permitAll()

            .and()
            .formLogin()
                .usernameParameter("login")
                .loginPage("/login")
                .defaultSuccessUrl("/users", true)
                .failureUrl("/login?error=true")
                .permitAll(); // failureUrl такой вариант работает, а с failureHandler = нет

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
        throws Exception
    {
        auth
            .inMemoryAuthentication()
            .withUser("user1")
            .password(passwordEncoder.encode("user1Pass"))
            .authorities("ROLE_USER");
    }
}
