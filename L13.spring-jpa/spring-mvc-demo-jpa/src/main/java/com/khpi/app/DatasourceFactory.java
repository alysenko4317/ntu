package com.khpi.app;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.IOException;
import java.util.Properties;

public class DatasourceFactory
{
    public static DriverManagerDataSource getDatasource(final String configFile)
    {
        DriverManagerDataSource ds = null;

        try
        {
            Properties properties = new FileHelper().loadProperties(configFile);

            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");
            String driverClassName = properties.getProperty("db.driverClassName");

            ds = new DriverManagerDataSource();  // spring-jdbc
            ds.setUsername(dbUsername);
            ds.setPassword(dbPassword);
            ds.setUrl(dbUrl);
            ds.setDriverClassName(driverClassName);

            System.out.println(dbUrl + dbUsername + dbPassword + driverClassName);
        }
        catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return ds;
    }
}
