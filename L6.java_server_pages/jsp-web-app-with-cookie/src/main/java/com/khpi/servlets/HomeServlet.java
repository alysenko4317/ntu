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
 * Сервлет, который работает со страницей home
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    // в случае GET-запроса следует просто отдать страницу home
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        request.getServletContext()
               .getRequestDispatcher("/jsp/home_2.jsp")
               .forward(request, response);
    }

    // обработка запроса, который должен поменять цвет заголовка
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        // получаем параметр запроса
        String color = request.getParameter("color");
        // создаем Cookie с данным значением
        Cookie colorCookie = new Cookie("color", color);
        // кладем в ответ
        response.addCookie(colorCookie);
        // перенаправляем пользователя обратно на страницу home чтобы добавленная
        // cookie была сразу применена
        response.sendRedirect(request.getContextPath() + "/home");
    }
}
