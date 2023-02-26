package com.khpi.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.khpi.service.forms.UserForm;
import com.khpi.service.models.Account;
import com.khpi.service.repositories.UsersRepository;
import com.khpi.service.services.UsersService;
import com.khpi.service.transfer.UserDto;

import java.util.List;

import static com.khpi.service.transfer.UserDto.from;


@RestController
public class UsersController
{
    @Autowired
    private UsersService usersService;

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return from(usersService.findAll());
    }

    @GetMapping("/users/{user-id}")
    public Account getUser(@PathVariable("user-id") Integer userId) {
        return usersService.findOne(userId);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> addUser(@RequestBody UserForm userForm) {
        usersService.signUp(userForm);
        return ResponseEntity.ok().build();
    }
}
