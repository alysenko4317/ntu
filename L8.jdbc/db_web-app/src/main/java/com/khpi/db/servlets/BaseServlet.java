package com.khpi.db.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BaseServlet extends HttpServlet
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

            // without this the following exception will be fired under Tomcat:
            //    No suitable driver found for jdbc:postgresql
            Class.forName(driverClassName);

            _connection = DriverManager.getConnection(url, username, password);
        }
        catch (IOException | SQLException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    Connection getConnection() {
        return _connection;
    }
}
