package com.khpi.db.servlets;

import com.khpi.db.models.Account;

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
        rq.setAttribute("usersFromServer", Account.fetchAll(getConnection()));

        rq.getServletContext()
          .getRequestDispatcher("/jsp/accounts.jsp")
          .forward(rq, rs);
    }
}
