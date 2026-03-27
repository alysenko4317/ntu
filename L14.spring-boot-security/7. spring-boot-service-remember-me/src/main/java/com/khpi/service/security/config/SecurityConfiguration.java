package com.khpi.service.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;


@Configuration
@ComponentScan("com.khpi")
@EnableWebSecurity
public class SecurityConfiguration   // extends WebSecurityConfigurerAdapter
{
    final private UserDetailsService userDetailsService;
    final private PasswordEncoder passwordEncoder;
    final private DataSource dataSource;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")  // IntelliJ fails to find DataSource bean
    public SecurityConfiguration(UserDetailsService uds, PasswordEncoder pe, DataSource ds) {
        userDetailsService = uds;
        passwordEncoder = pe;
        dataSource = ds;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http
            .csrf().disable()

            .authorizeHttpRequests()
                .requestMatchers("/users**").hasAuthority("ADMIN") //authenticated()
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
                .permitAll() // failureUrl - цей варіант працює, а з failureHandler = ні
            // як зробити так, щоб не потрібно було логінитись повторно при закритті браузера
            .and()
            // how to avoid re-login when the browser is closed
            .rememberMe()
                // це назва параметру на формі, який відповідає за цей функціонал
                .rememberMeParameter("remember-me")
                // репозиторій де будуть зберігатися токени
                .tokenRepository(tokenRepository());

        return http.build();
    }

    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        // dataSource вказує БД, з якою треба працювати
        // також дивись schema.sql
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
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
