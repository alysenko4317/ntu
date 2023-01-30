package com.khpi.servlets;

import com.khpi.repositories.UsersRepository;
import com.khpi.repositories.UsersRepositoryInMemoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 14.03.2018
 * Сервлет, позволяющий польователю войти в систему
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    // ссылка на хранилище пользователей
    private UsersRepository usersRepository;

    @Override
    public void init() throws ServletException {
        this.usersRepository = new UsersRepositoryInMemoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs)
        throws ServletException, IOException
    {
        rq.getServletContext()
          .getRequestDispatcher("/jsp/login.jsp")
          .forward(rq, rs);
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs)
        throws ServletException, IOException
    {
        // вытаскиваем из запроса имя пользователя и его пароль
        String name = rq.getParameter("name");
        String password = rq.getParameter("password");

        // авторизация - если пользователь есть в системе
        if (usersRepository.isExist(name, password))
        {
            // создаем для него сессию
            HttpSession session = rq.getSession();
            // кладем в атрибуты сессии атрибут user с именем пользователя
            session.setAttribute("user", name);
            // перенаправляем на страницу home
            rq.getServletContext()
              .getRequestDispatcher("/home")
              .forward(rq, rs);
        }
        else
        {
            rs.sendRedirect(rq.getContextPath() + "/login");
        }
    }
}
