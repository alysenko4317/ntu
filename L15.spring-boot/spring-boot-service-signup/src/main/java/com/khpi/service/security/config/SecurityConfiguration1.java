package com.khpi.service.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@ComponentScan("com.khpi")
@EnableWebSecurity
public class SecurityConfiguration1 //extends WebSecurityConfigurerAdapter
{
    //@Autowired
    final private UserDetailsService userDetailsService;

    //@Autowired
    final private PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfiguration1(UserDetailsService uds, PasswordEncoder pe) {
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
                // css-ки тоже доступны всем для подключения к html-страничкам
                .requestMatchers("/css/**").permitAll()
                // страница регистрации - доступна всем
                .requestMatchers("/signup/**").permitAll()
                // корень разрешаем для всех, если пользователь не залогинен, редирект на /login
                // выполнится в контроллере ProfileController (он обрабатывает запрос к корневому URL)
                .requestMatchers("/").permitAll()
            .and()
            .formLogin()
                .usernameParameter("login")  // by default spring uses "username", but we named it "login"
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
                .permitAll(); // failureUrl такой вариант работает, а с failureHandler = нет

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http)
        throws Exception
    {
        return
            http
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }
}
