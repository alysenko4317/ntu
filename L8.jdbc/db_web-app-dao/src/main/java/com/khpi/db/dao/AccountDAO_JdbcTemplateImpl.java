package com.khpi.db.dao;

import com.khpi.db.models.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AccountDAO_JdbcTemplateImpl implements AccountDAO
{
    private JdbcTemplate _template;

    // language=SQL
    private static final String SQL_SELECT_ALL =
        "SELECT * FROM car_portal_app.account ORDER BY first_name";

    // language=SQL
    private static final String SQL_SELECT_BY_ID =
        "SELECT * FROM car_portal_app.account WHERE account_id = ?";

    // language=SQL
    private final String SQL_SELECT_ALL_BY_FIRST_NAME =
        "SELECT * FROM car_portal_app.account WHERE first_name = ?";

    private Map<Integer, Account> _accounts = new HashMap<>();

    public AccountDAO_JdbcTemplateImpl(DataSource dataSource)
    {
        _template = new JdbcTemplate(dataSource);
    }

    private RowMapper<Account> accountRowMapper = (ResultSet rs, int i) ->
    {
       Integer id = rs.getInt("account_id");

       if (! _accounts.containsKey(id))
       {
           _accounts.put(id, new Account(
               id,
               rs.getString("first_name"),
               rs.getString("last_name"),
               rs.getString("email"),
               rs.getString("password")));
       }

       return _accounts.get(id);
    };

    @Override
    public List<Account> findAllByFirstName(String firstName) {
        return _template.query(SQL_SELECT_ALL_BY_FIRST_NAME, accountRowMapper, firstName);
    }

    @Override
    public Optional<Account> find(Integer id)
    {
        _template.query(SQL_SELECT_BY_ID, accountRowMapper, id);

        if (_accounts.containsKey(id)) {
            return Optional.of(_accounts.get(id));
        }
        return Optional.empty();
    }

    @Override
    public void save(Account model)
    {
        try
        {
            Connection conn = _template.getDataSource().getConnection();

            PreparedStatement ps = conn.prepareStatement(
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
    public void update(Account model) {
    }

    @Override
    public void delete(Integer id) {
    }

    @Override
    public List<Account> fetchAll() {
        return _template.query(SQL_SELECT_ALL, accountRowMapper);
    }
}
