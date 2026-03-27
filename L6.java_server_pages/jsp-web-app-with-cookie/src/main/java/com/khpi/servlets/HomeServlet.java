package com.khpi.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HomeServlet
 * Сервлет, що працює зі сторінкою home
 */
@WebServlet("/home")
    // у випадку GET-запиту слід просто віддати сторінку home

    // for a GET request simply forward to the home page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        request.getServletContext()
               .getRequestDispatcher("/jsp/home_2.jsp")
    // обробка запиту, який має змінити колір заголовка
    }

    // handle the request that should change the header color
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        // отримуємо параметр запиту
    {
        // get the request parameter
        // створюємо Cookie з цим значенням
        // create a Cookie with this value
        // додаємо у відповідь
        // add it to the response
        response.addCookie(colorCookie);
        // перенаправляємо користувача назад на сторінку home, щоб доданий
        // cookie було застосовано одразу
        response.sendRedirect(request.getContextPath() + "/home");
    }
}
