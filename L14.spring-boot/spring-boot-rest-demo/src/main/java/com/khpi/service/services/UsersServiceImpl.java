package com.khpi.service.services;

import com.khpi.service.forms.UserForm;
import com.khpi.service.models.Account;
import com.khpi.service.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UsersServiceImpl implements UsersService
{
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(UserForm userForm) {
        String hashPassword = passwordEncoder.encode(userForm.getPassword());

        Account user = Account.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .passwordHash(hashPassword)
                .email(userForm.getLogin())
                .role(Account.Role.USER)
                .state(Account.State.ACTIVE)
                .build();

        usersRepository.save(user);
    }

    @Override
    public List<Account> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public Account findOne(Integer userId) {
        return null;//usersRepository.findOne(userId); FIXME
    }
}
