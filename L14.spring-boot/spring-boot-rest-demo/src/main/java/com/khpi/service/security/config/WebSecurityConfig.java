package com.khpi.service.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@ComponentScan("ru.ivmiit")
@EnableWebSecurity
public class WebSecurityConfig
{
    @Autowired
    private AuthenticationProvider authenticationProvider;

    // @Autowired
    //private TokenAuthFilter tokenAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //.addFilterBefore(tokenAuthFilter, BasicAuthenticationFilter.class)
               // .antMatcher("/**")
               // .authenticationProvider(authenticationProvider)
               // .authorizeRequests()
               // .antMatchers("/users/**").hasAuthority("USER")
               // .antMatchers("/login").permitAll();
        .csrf().disable();

        return http.build();
    }
}
