package com.khpi.servlets;

import com.khpi.models.User;
import com.khpi.repositories.UsersRepository;
import com.khpi.repositories.UsersRepositoryInMemoryImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
        List<User> users = usersRepository.findAll();

        request.setAttribute("usersFromServer", users);
        RequestDispatcher dispatcher =
            request.getServletContext().getRequestDispatcher("/jsp/signUp_1.jsp");

        dispatcher.forward(request, response);
    }
}
