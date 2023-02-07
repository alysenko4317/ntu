package com.khpi.db.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Account
{
    public Account(final ResultSet rs) throws SQLException
    {
        firstName = rs.getString("first_name");
        lastName = rs.getString("last_name");
        email = rs.getString("email");
        pwdHash = rs.getString("password");
    }

    public static List<Account> fetchAll(Connection connection)
    {
        List<Account> accounts = new ArrayList<>();

        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(
                "SELECT * FROM car_portal_app.account ORDER BY first_name");

            while (rs.next())
                accounts.add(new Account(rs));
        }
        catch (SQLException e) {
            throw new IllegalStateException(e);
        }

        return accounts;
    }

    // getters are required by JSP

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    private String firstName;
    private String lastName;
    private String email;
    private String pwdHash;
}
