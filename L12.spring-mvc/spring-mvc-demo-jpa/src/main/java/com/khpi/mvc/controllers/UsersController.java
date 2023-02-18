package com.khpi.mvc.controllers;

import com.khpi.mvc.forms.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.khpi.mvc.dao.AccountDAO;
//import com.khpi.mvc.forms.UserForm;
import com.khpi.mvc.models.Account;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Controller
public class UsersController
{
    @Autowired
    private AccountDAO accountsDAO;

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public ModelAndView getAllUsers(@RequestParam(value = "fname", required = false) String firstName)
    {
        List<Account> users = null;

        if (firstName != null) {
            users = accountsDAO.findAllByFirstName(firstName);
        } else {
            users = accountsDAO.fetchAll();
        }
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("usersFromServer", users);
        return modelAndView;
    }

    // hint: попутно расказать про NamedParameterJdbcTemplate в сравнении с обычным JdbcTemplate
    @RequestMapping(path = "/users/{user-id}", method = RequestMethod.GET)
    public ModelAndView getUserById(@PathVariable("user-id") Integer userId)
    {
        Optional<Account> userCandidate = accountsDAO.find(userId);
        ModelAndView modelAndView = new ModelAndView("users");
        userCandidate.ifPresent(user -> modelAndView.addObject("usersFromServer",
                Collections.singletonList(user)));
        return modelAndView;
    }

    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public String addUser(UserForm userForm)   // снова магия!
    {
        System.out.println(userForm);
        Account newUser = Account.from(userForm);
        System.out.println(newUser.toString());
        accountsDAO.save(newUser);
        return "redirect:/users";
    }
}
