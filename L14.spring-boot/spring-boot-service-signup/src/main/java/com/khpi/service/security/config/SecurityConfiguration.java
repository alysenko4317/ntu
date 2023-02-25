package com.khpi.service.security.config;

import ch.qos.logback.core.encoder.Encoder;
import com.khpi.service.models.Account;
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

import javax.sql.DataSource;

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
                .defaultSuccessUrl("/users", true)
                .failureUrl("/login?error=true")
                .permitAll(); // failureUrl такой вариант работает, а с failureHandler = нет

        return http.build();
    }
/*
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
*/

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, DataSource dataSource)
        throws Exception
    {
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .withDefaultSchema()
            .usersByUsernameQuery(
                "select email, password, 'true' from account where email=?")
            .authoritiesByUsernameQuery(
                "select email, role from account where email=?");

         /*   .withUser(Account.withUsername("user")
                             .password("password")
                             .roles("USER"))
            .withUser(Account.withUsername("admin")
                             .password("password")
                             .roles("ADMIN"));*/
    }

    /*
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
    }*/
}
