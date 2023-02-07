package com.khpi.db.servlets;

import com.google.common.hash.Hashing;
import com.khpi.db.dao.AccountDAO;
import com.khpi.db.dao.AccountDAO_JdbcImpl;
import com.khpi.db.models.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/addAccount")
public class AddAccountServlet extends BaseServlet
{
    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs)
            throws ServletException, IOException
    {
        rq.getServletContext()
          .getRequestDispatcher("/jsp/addAccount.jsp")
          .forward(rq, rs);
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs)
            throws ServletException, IOException
    {
        final String firstName = rq.getParameter("first-name");
        final String lastName = rq.getParameter("last-name");
        final String password = rq.getParameter("password");
        final String email = rq.getParameter("email");
        final String passwordHash = Hashing.sha512().hashString(password, StandardCharsets.UTF_8).toString();

        AccountDAO dao = new AccountDAO_JdbcImpl(getDataSource());
        dao.save(new Account(firstName, lastName, email, passwordHash));

        rs.sendRedirect(rq.getContextPath() + "/accounts");
    }
}
