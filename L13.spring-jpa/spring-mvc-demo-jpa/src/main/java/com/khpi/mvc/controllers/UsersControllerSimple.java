package com.khpi.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import com.khpi.mvc.dao.AccountDAO;
import com.khpi.mvc.models.Account;

//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

public class UsersControllerSimple implements Controller
{
    @Autowired
    private AccountDAO _accountsDAO;    // этот объект создаётся неявно! магия!
    // потому что @Autowired, а интерфейс AccountDAO реализует класс AccountDAO_JdbcImpl,
    // этот класс, в свою очередь, помечен анотацией @Component, и он единственый,
    // кто реализует интерфейс AccountDAO.

    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse) throws Exception
    {
        if (httpServletRequest.getMethod().equals("GET"))
        {
            List<Account> accounts = _accountsDAO.fetchAll();
            //for(Account acc: accounts)
            //    System.out.println(acc);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("users");
            modelAndView.addObject("usersFromServer", accounts);
            return modelAndView;
        }

        return null;
    }
}
