package com.khpi.servlets;

import com.khpi.models.User;
import com.khpi.repositories.UsersRepository;
import com.khpi.repositories.UsersRepositoryInMemoryImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet
{
    private UsersRepository usersRepository;

    @Override
    public void init() throws ServletException {
        this.usersRepository = new UsersRepositoryInMemoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        System.out.println("doGet");

        List<User> users = usersRepository.findAll();

        request.setAttribute("usersFromServer", users);
        RequestDispatcher dispatcher =
            request.getServletContext().getRequestDispatcher("/jsp/signUp_3.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        System.out.println("doPost");

        // вытащили данные регистрации
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"));

        // создали пользователя и сохранили его в хранилище
        User user = new User(name, password, birthDate);
        usersRepository.save(user);

        // Be careful with your relative URLS:
        //   - adding a / to the path makes it absolute.
        //   - adding ../ makes it go to the parent of the current directory.
        //
        // https://stackoverflow.com/questions/45886966/servlet-404-error-on-get-request-upon-submitting-the-form
        // https://stackoverflow.com/questions/4764405/how-to-use-relative-paths-without-including-the-context-root-name

        response.sendRedirect("hello");
    }
}
