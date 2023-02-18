package com.khpi.mvc.controllers;

import com.khpi.mvc.forms.UserForm;
import com.khpi.mvc.models.Account;
import com.khpi.mvc.models.Car;
import com.khpi.mvc.repositories.CarsRepository;
import com.khpi.mvc.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UsersWithJpaController {

    @Autowired  // снова магия!
    private UsersRepository usersRepository;

    @RequestMapping(path = "/jpa/users", method = RequestMethod.GET)
    public ModelAndView getUsers(
        @RequestParam(required = false, name = "fname") String firstName)
    {
        List<Account> users = (firstName != null)
            ? usersRepository.findAllByFirstName(firstName)
            : usersRepository.findAllWithOwnersCount();

        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("usersFromServer", users);

        return modelAndView;
    }

    @RequestMapping(path = "/jpa/users", method = RequestMethod.POST)
    public String addUser(UserForm userForm) {
        Account newUser = Account.from(userForm);
        usersRepository.save(newUser);
        return "redirect:/jpa/users";
    }
}
