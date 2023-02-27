package com.khpi.service.controllers;

import com.khpi.service.forms.UserForm;
import com.khpi.service.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.khpi.service.models.Account;
import java.util.List;
import java.util.Optional;


@RestController
public class UsersController
{
    @Autowired
    private UsersService usersService;

    @GetMapping("/users")
    public List<Account> getUsers() {
        return usersService.findAll();
    }

    @GetMapping("/users/{user-id}")
    public Optional<Account> getUser(@PathVariable("user-id") Integer userId) {
        return usersService.findById(userId);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> addUser(@RequestBody UserForm userForm) {
        usersService.signUp(userForm);
        return ResponseEntity.ok().build();
    }
}
