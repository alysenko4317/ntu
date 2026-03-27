package com.khpi.service.services;

import com.khpi.service.app.PasswordEncoder;
import com.khpi.service.forms.UserForm;
import com.khpi.service.models.Account;
import com.khpi.service.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class UsersServiceImpl implements UsersService
{
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

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
    public Optional<Account> findById(Integer userId) {
        return usersRepository.findById(userId);
    }
}
