package com.khpi.service.security.details;

import com.khpi.service.models.Account;
import com.khpi.service.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException
    {
        Optional<Account> userCandidate = usersRepository.findOneByEmail(login);
        if (userCandidate.isPresent()) {
            return new UserDetailsImpl(userCandidate.get());
        }
        else
            throw new IllegalArgumentException("User not found");

       // return new
       //     UserDetailsImpl(usersRepository.findOneByEmail(login)
       //        .orElseThrow(IllegalArgumentException::new));
    }
}
