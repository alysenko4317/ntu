package com.khpi.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.khpi.service.models.Account;
import com.khpi.service.repositories.UsersRepository;
import java.util.List;
import java.util.Optional;

// використовується спеціальна анотація, яка схожа зі звичайним @Controller, але надає
//   додаткові плюшки, наприклад можна відповідати безпосередньо даними, і вони будуть
//   автоматично сконвертовані у json
@RestController
public class UsersController
{
    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/users")
    public List<Account> getUsers() {
        return usersRepository.findAll();
    }

    @GetMapping("/users/{user-id}")
    public Optional<Account> getUser(@PathVariable("user-id") Integer userId) {
        return usersRepository.findById(userId);
    }
}
