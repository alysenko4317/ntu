package ru.ivmiit.db.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

@WebServlet("/accounts")
public class AccountsServlet extends HttpServlet
{
    private Connection _connection;

    @Override
    public void init() throws ServletException
    {
        Properties properties = new Properties();

        try
        {
            properties.load(
                new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/db.properties")));

            final String url = properties.getProperty("db.url");
            final String username = properties.getProperty("db.username");
            final String password = properties.getProperty("db.password");
            final String driverClassName = properties.getProperty("db.driverClassName");

            System.out.println(username + ":" + password);
            System.out.println(url);

            Class.forName(driverClassName);
            _connection = DriverManager.getConnection(url, username, password);
        }
        catch (IOException | SQLException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

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
        String firstName = rq.getParameter("first-name");
        String lastName = rq.getParameter("last-name");

        try
        {
//            Statement statement = connection.createStatement();
//            String sqlInsert = "INSERT INTO fix_user(first_name, last_name)" +
//                    "VALUES('" + firstName + "','" + lastName + "');";
//            System.out.println(sqlInsert);
//            statement.execute(sqlInsert);

            PreparedStatement preparedStatement =
                _connection.prepareStatement(
                     "INSERT INTO " + "fix_user(first_name, last_name) VALUES (?, ?)");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.execute();
        }
        catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
