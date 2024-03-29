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
                .permitAll() // failureUrl такой вариант работает, а с failureHandler = нет
            .and()
            // як зробити так, щоб не потрыбно було логынитись повторно при закритті браузеру
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
