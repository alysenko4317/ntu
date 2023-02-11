package com.khpi.db.servlets;

import com.google.common.hash.Hashing;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/addAccount")
public class CreateAccountServlet extends BaseServlet
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
        final String pwdhash = Hashing.sha512().hashString(password, StandardCharsets.UTF_8).toString();

        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(email);
        System.out.println(password);

        try
        {
            if (false)
            {
                // SQL Injection vulnerability!
                //    temp','temp','temp','temp'); DROP TABLE car_portal_app.account; -- 1

                String sqlInsert =
                        "INSERT INTO car_portal_app.account(first_name, last_name, email, password) " +
                                "VALUES('" + firstName + "','" + lastName + "','" + email + "','" + pwdhash + "');";

                System.out.println(sqlInsert);
                //getConnection().createStatement().execute(sqlInsert);
            }
            else
            {
                PreparedStatement preparedStatement =
                    getConnection().prepareStatement(
                        "INSERT INTO " + "car_portal_app.account(first_name, last_name, email, password) "
                                + "VALUES (?, ?, ?, ?)");

                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, pwdhash);

                preparedStatement.execute();
            }

            rs.sendRedirect(rq.getContextPath() + "/accounts");
        }
        catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
