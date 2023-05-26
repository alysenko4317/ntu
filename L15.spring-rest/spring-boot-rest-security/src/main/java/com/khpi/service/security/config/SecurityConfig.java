package com.khpi.service.security.config;

import com.khpi.service.security.filters.TokenAuthFilter;
import com.khpi.service.security.filters.TokenAuthFilter2;
import com.khpi.service.security.provider.TokenAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
@ComponentScan("com.khpi")
@EnableWebSecurity
public class SecurityConfig
{
    @Autowired
    private TokenAuthenticationProvider authProvider;

    @Bean
    public AuthenticationManager authManager(HttpSecurity http)
        throws Exception
    {
        AuthenticationManagerBuilder authenticationManagerBuilder =
            http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authProvider);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, TokenAuthFilter tokenAuthFilter)
        throws Exception
    {
        http = http
            .sessionManagement()   // set session management to stateless
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and();

        http
            .authenticationProvider(authProvider)

            .addFilterBefore(tokenAuthFilter, BasicAuthenticationFilter.class)

            .authorizeHttpRequests()
                .requestMatchers("/users/**").authenticated()
                .requestMatchers("/login/**").permitAll()

            .and()   // disable CORS and disable CSRF
               .csrf().disable()
               .cors().disable();

        return http.build();
    }
}
