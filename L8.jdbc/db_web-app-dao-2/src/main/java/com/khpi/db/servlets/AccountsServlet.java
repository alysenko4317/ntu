package com.khpi.db.servlets;

import com.khpi.db.dao.*;

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
        AccountDAO dao = new AccountDAO_JdbcImpl(
            getDataSource(),
            rq.getParameter("cars") != null);

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
