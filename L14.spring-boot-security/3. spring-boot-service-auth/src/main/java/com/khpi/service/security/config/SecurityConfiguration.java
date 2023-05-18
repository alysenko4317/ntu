package com.khpi.service.security.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;

/*
 *** 3. Demonstration of .failureHandler customization ***
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
            .csrf().disable()

            .authorizeHttpRequests()
                .requestMatchers("/users/**").authenticated()
                .requestMatchers("/css/**").permitAll()
                // можно также указать .permitAll() в секции .formLogin(), однако
                // в некоторых случаях этого недостаточно. Например, если используется
                // .failureHandler, который устанавливает setDefaultFailureUrl с параметром
                // ?error, то без правила /login** обращение к  /login?error будет перенаправлено
                // на /login (уже без параметра ?error)и пользователь не увидит сообщение об ошибке.
                .requestMatchers("/login**").permitAll()

            .and()
            .formLogin()
                .loginPage("/login")
                .usernameParameter("login")
                .defaultSuccessUrl("/users", true)
                .failureHandler(new SimpleUrlAuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request,
                                                        HttpServletResponse response,
                                                        AuthenticationException exception)
                        throws IOException, ServletException
                    {
                        final String email = request.getParameter("login");
                        final String error = exception.getMessage();
                        System.out.println(
                            "A failed login attempt with email: " + email + ". Reason: " + error);
                        super.setDefaultFailureUrl("/login?error=1");
                        super.onAuthenticationFailure(request, response, exception);
                    }
                });

        return http.build();
    }
}
