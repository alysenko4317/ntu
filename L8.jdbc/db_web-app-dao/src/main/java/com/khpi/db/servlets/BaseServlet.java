package com.khpi.db.servlets;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class BaseServlet extends HttpServlet
{
    private DriverManagerDataSource _ds;

    public void init() throws ServletException
    {
        Properties properties = new Properties();

        try
        {
            properties.load(
                new FileInputStream(
                       getServletContext().getRealPath("/WEB-INF/classes/db.properties")));

            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");
            String driverClassName = properties.getProperty("db.driverClassName");

            // DriverManagerDataSource - объект, который содержит в себе всю информацию о подключении

            _ds = new DriverManagerDataSource();  // spring-jdbc
            _ds.setUsername(dbUsername);
            _ds.setPassword(dbPassword);
            _ds.setUrl(dbUrl);
            _ds.setDriverClassName(driverClassName);

            System.out.println(dbUrl + dbUsername + dbPassword + driverClassName);
        }
        catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    DriverManagerDataSource getDataSource() {
        return _ds;
    }
}
