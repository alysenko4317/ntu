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
 * Сервлет, що дозволяє користувачу увійти в систему
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    // посилання на сховище користувачів
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
        // витягуємо із запиту ім'я користувача та його пароль
        String name = rq.getParameter("name");
        String password = rq.getParameter("password");

        // авторизація - якщо користувач є в системі
        if (usersRepository.isExist(name, password))
        {
            // створюємо для нього сесію
            HttpSession session = rq.getSession();
            // кладемо в атрибути сесії атрибут user з ім'ям користувача
            session.setAttribute("user", name);
            // перенаправляємо на сторінку home
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
