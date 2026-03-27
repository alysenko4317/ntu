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

// по факту UsersControllerSimple
//    - це сервлет, але трошки особливий у тому плані що він інтегрується зі Spring

public class UsersControllerSimple implements Controller
{
    @Autowired
    private AccountDAO _accountsDAO;    // цей об'єкт створюється неявно! магія!
    // тому що @Autowired, а інтерфейс AccountDAO реалізує клас AccountDAO_JdbcImpl,
    // цей клас, у свою чергу, позначений анотацією @Component, і він єдиний,
    // хто реалізує інтерфейс AccountDAO.

    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse) throws Exception
    {
        // відповідаємо тільки на запити типу GET
        if (httpServletRequest.getMethod().equals("GET"))
        {
            List<Account> accounts = _accountsDAO.fetchAll();
            //for(Account acc: accounts)
            //    System.out.println(acc);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("users");  // users.jps
            modelAndView.addObject("usersFromServer", accounts);
            return modelAndView;
        }

        return null;
    }
}
