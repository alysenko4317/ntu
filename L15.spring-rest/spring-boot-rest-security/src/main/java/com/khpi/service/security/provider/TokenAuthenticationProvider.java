package com.khpi.service.security.provider;

import com.khpi.service.models.Token;
import com.khpi.service.repositories.TokensRepository;
import com.khpi.service.security.token.TokenAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import java.util.Optional;

// задаёт как именно обрабатывать авторизацию

// Token Authentication Provider
// The AuthenticationProvider is responsible to find user based on the authentication token sent
// by the client in the header. This is how our Spring based token authentication provider looks like:

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private TokensRepository tokensRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication)
        throws AuthenticationException
    {
        // сюди може прийти тільки TokenAuthentication (в нашому конкретному випадку)
        // тому робимо просто каст до нашого типу
        TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;

        // знаходимо тонен у БД
        Optional<Token> tokenCandidate = tokensRepository.findOneByValue(tokenAuthentication.getName());

        if (tokenCandidate.isPresent())
        {
            // згідно токену знаходимо користувача
            UserDetails userDetails =
                userDetailsService.loadUserByUsername(
                    tokenCandidate.get().getAccount().getEmail());

            // встановлюемо користувача для Spring та флаг того що авторизація пройдена
            tokenAuthentication.setUserDetails(userDetails);
            tokenAuthentication.setAuthenticated(true);

            return tokenAuthentication;
        }
        else
            throw new IllegalArgumentException("Bad token");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // тут вказується що наш auth-provider вміє працювати тільки з
        // TokenAuthentication типом об'єктів авторизаціі
        return TokenAuthentication.class.equals(authentication);
    }
}
