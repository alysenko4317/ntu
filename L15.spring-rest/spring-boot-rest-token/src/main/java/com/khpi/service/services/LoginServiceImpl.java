package com.khpi.service.services;

import com.khpi.service.app.PasswordEncoder;
import com.khpi.service.forms.LoginForm;
import com.khpi.service.models.Account;
import com.khpi.service.models.Token;
import com.khpi.service.repositories.TokensRepository;
import com.khpi.service.repositories.UsersRepository;
import com.khpi.service.transfer.TokenDto;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;
import static com.khpi.service.transfer.TokenDto.from;

@Component
public class LoginServiceImpl implements LoginService
{
    @Autowired
    private TokensRepository tokensRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public TokenDto login(LoginForm loginForm)
    {
        Optional<Account> userCandidate = usersRepository.findOneByEmail(loginForm.getLogin());

        if (userCandidate.isPresent())
        {
            Account user = userCandidate.get();

            if (passwordEncoder.matches(loginForm.getPassword(), user.getPasswordHash()))
            {
                Token token = Token.builder()
                        .account(user)
                        .value(RandomStringUtils.random(10, true, true))
                        .build();

                tokensRepository.save(token);
                return from(token);
            }
        }
        throw new IllegalArgumentException("User not found");
    }
}
