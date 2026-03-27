package com.khpi.service.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@ComponentScan("com.khpi")
@EnableWebSecurity
public class SecurityConfiguration   // extends WebSecurityConfigurerAdapter
{
    final private UserDetailsService userDetailsService;
    final private PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfiguration(UserDetailsService uds, PasswordEncoder pe) {
        userDetailsService = uds;
        passwordEncoder = pe;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http
            .csrf().disable()

            .authorizeHttpRequests()
                .requestMatchers("/users**").authenticated()
                // css-ки також доступні всім для підключення до html-сторінок
                .requestMatchers("/css/**").permitAll()
                // сторінка реєстрації - доступна всім
                .requestMatchers("/signup/**").permitAll()
                // корінь дозволяємо для всіх, якщо користувач не залогінений, редірект на /login
                // виконається в контролері ProfileController (він обробляє запит до кореневого URL)
                .requestMatchers("/").permitAll()

            .and()
            .formLogin()
                .usernameParameter("login")  // by default spring uses "username", but we named it "login"
                .loginPage("/login")
                .defaultSuccessUrl("/users", true)
                .permitAll(); // failureUrl такий варіант працює, а з failureHandler = ні
                .permitAll(); // failureUrl - цей варіант працює, а з failureHandler = ні

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http)
        throws Exception
    {
        return
            http
                .getSharedObject(AuthenticationManagerBuilder.class)
                // встановлюємо який сервіс повинен використовувати Spring для завантаження даних про користувача
                .userDetailsService(userDetailsService)
                // встановлюємо password encoder повинен використовувати Spring для роботи з паролями
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }
}
