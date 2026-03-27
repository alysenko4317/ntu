package com.khpi.db.servlets;

import com.khpi.db.dao.AccountDAO;
import com.khpi.db.dao.AccountDAO_JdbcImpl;
import com.khpi.db.dao.AccountDAO_JdbcTemplateImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/accounts")
public class AccountsServlet extends BaseServlet
{
    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs)
        throws ServletException, IOException
    {
        //AccountDAO dao = new AccountDAO_JdbcImpl(getDataSource());
        AccountDAO dao = new AccountDAO_JdbcTemplateImpl(getDataSource());

        if (rq.getParameter("fn") != null)
        {
            String firstName = rq.getParameter("fn");
            System.out.println(firstName);
            rq.setAttribute("usersFromServer", dao.findAllByFirstName(firstName));
        }
        else
        {
            rq.setAttribute("usersFromServer", dao.fetchAll());
        }

        rq.getServletContext()
          .getRequestDispatcher("/jsp/accounts.jsp")
          .forward(rq, rs);
    }
}
