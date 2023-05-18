package com.khpi.service.controllers;

import com.khpi.service.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersController
{
    @Autowired
    private UsersRepository usersRepository;

    @Value("${my.property}")
    private String myProperty;

    @GetMapping("/users")
    public String getUsersPage(ModelMap model) {
        System.out.println("MY PROPERTY = " + myProperty);
        model.addAttribute("usersFromServer", usersRepository.findAll());
        return "users";
    }
}
