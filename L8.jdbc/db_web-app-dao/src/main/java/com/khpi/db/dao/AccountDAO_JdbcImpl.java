package com.khpi.db.dao;

import com.khpi.db.models.Account;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDAO_JdbcImpl implements AccountDAO {

    // language=SQL
    private static final String SQL_SELECT_ALL =
        "SELECT * FROM car_portal_app.account ORDER BY first_name";

    // language=SQL
    private static final String SQL_SELECT_BY_ID =
        "SELECT * FROM car_portal_app.account WHERE account_id = ?";

    private Connection _connection;

    public AccountDAO_JdbcImpl(DataSource dataSource)
	{
        try
		{
            _connection = dataSource.getConnection();
        }
		catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Account> findAllByFirstName(String firstName) {
        return null;
    }

    @Override
    public Optional<Account> find(Integer id)
	{
        try
		{
            PreparedStatement statement = _connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next())
			{
                return Optional.of(new Account(
                    id,
                    rs.getString("first_name"),
					rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("password")));
            }
			
            return Optional.empty();
        }
		catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(Account model)
    {
        try {
            PreparedStatement ps = _connection.prepareStatement(
                "INSERT INTO " + "car_portal_app.account(first_name, last_name, email, password) " +
                        "VALUES (?, ?, ?, ?)");

            ps.setString(1, model.getFirstName());
            ps.setString(2, model.getLastName());
            ps.setString(3, model.getEmail());
            ps.setString(4, model.getPasswordHash());

            ps.execute();
        }
        catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void update(Account model) { }

    @Override
    public void delete(Integer id) { }

    @Override
    public List<Account> fetchAll()
	{
        try
		{
            List<Account> accounts = new ArrayList<>();
            Statement statement = _connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL);
            while (rs.next())
			{
                accounts.add(new Account(
                    rs.getInt("account_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("password")));
            }
			
            return accounts;
        }
        catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
