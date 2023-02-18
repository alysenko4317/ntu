package com.khpi.mvc.dao;

import com.khpi.mvc.models.Account;
import com.khpi.mvc.models.Car;
import com.khpi.mvc.models.CarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Component
public class AccountDAO_JdbcImpl implements AccountDAO
{
    private JdbcTemplate _template;
    private NamedParameterJdbcTemplate _templateNP;   // другой вариант для общего развития

    private boolean _loadOwnedCars;
    private Map<Integer, Account> _accounts = new HashMap<>();

    // language=SQL
    private static final String SQL_SELECT_ALL =
        "SELECT a.*, COUNT(b.account_id) AS cars_owned_count FROM car_portal_app.account a " +
            "LEFT JOIN car_portal_app.car_account_link b ON a.account_id = b.account_id " +
            "GROUP BY a.account_id, a.first_name " +
            "ORDER BY a.first_name";

    // language=SQL
    private static final String SQL_SELECT_ALL_WITH_CARS =
        "SELECT a.*, c.*, m.* FROM car_portal_app.account a " +
        "LEFT JOIN car_portal_app.car_account_link b ON a.account_id = b.account_id " +
        "LEFT JOIN car_portal_app.car c ON b.car_id = c.car_id " +
        "LEFT JOIN car_portal_app.car_model m ON c.car_model_id = m.car_model_id " +
        "ORDER BY a.first_name";

    // language=SQL
    private static final String SQL_SELECT_BY_ID =
        "SELECT * FROM car_portal_app.account WHERE account_id = ?";

    // language=SQL
    private final String SQL_SELECT_ALL_BY_FIRST_NAME =
        "SELECT * FROM car_portal_app.account WHERE first_name = ?";

    // language=SQL
    private static final String SQL_SELECT_ALL_BY_FIRST_NAME_2 =
        "SELECT a.*, COUNT(b.account_id) AS cars_owned_count FROM car_portal_app.account a " +
            "LEFT JOIN car_portal_app.car_account_link b ON a.account_id = b.account_id " +
            "WHERE a.first_name = ?" +
            "GROUP BY a.account_id, a.first_name " +
            "ORDER BY a.first_name ";

    // language=SQL
    private static final String SQL_SELECT_ALL_BY_FIRST_NAME_WITH_CARS =
        "SELECT a.*, c.*, m.* FROM car_portal_app.account a " +
            "LEFT JOIN car_portal_app.car_account_link b ON a.account_id = b.account_id " +
            "LEFT JOIN car_portal_app.car c ON b.car_id = c.car_id " +
            "LEFT JOIN car_portal_app.car_model m ON c.car_model_id = m.car_model_id " +
            "WHERE a.first_name = ?" +
            "ORDER BY a.first_name";

    @Autowired
    public AccountDAO_JdbcImpl(DataSource dataSource)
    {
        this(dataSource, false);
    }

    public AccountDAO_JdbcImpl(DataSource dataSource, boolean loadOwnedCars)
    {
        _template = new JdbcTemplate(dataSource);
        _loadOwnedCars = loadOwnedCars;

        _templateNP = new NamedParameterJdbcTemplate(dataSource);
    }

    private RowMapper<Account> accountRowMapper = (ResultSet rs, int i) ->
    {
        // ПРОБЛЕМА:
        // Так как мы используем left join для выборки из базы данных пользователей вместе с ихними
        // машинами, то при наличи у пользователя нескольких машин мы получим несколько строк, которые
        // будут отличаться только данными о машинах; т.е. данные о пользователе будут повторяться в
        // этих строках. Для корректного отображения реляционного представления данных в БД в
        // ООП-представление нам необходимо эту ситуацию обрабатывать специальным образом, чтобы метод
        // fetchAll() не отдавал этих дубликатов.
        //
        // РЕШЕНИЕ:
        // Мы используем контейнер вида Map<Integer, Account> для того чтобы после создания первого
        // объекта Account все последующие дубликаты не создавать, но добавлять даные о машинах в этот
        // Account-объект.
        //
        // Также стоит отметить два режима работы этого DAO класса, которые задаются параметром
        // _loadOwnedCars (это сделано для оптимизации)
        //    при _loadOwnedCars == true будут получены все данные, как о пользователях, так и о связанных
        //      с ними машинах; но это не всегда нужно, т.е. часто нужны данные тольк о пользователях, для
        //      этого имеется второй режим;
        //    при _loadOwnedCars == false данные о машинах получаться не будут из БД, однако будет получена
        //      информация о том, владеет ли пользователь какими-лио машинами в принципе:
        //         - объект Account будет создаваться с параметром cars=new ArrayList<>(), если у пользователя
        //           в принципе есть какие-либо машины в собственности;
        //         - объект Account будет создаваться с параметром cars=null, если у пользователя
        //           не имеется машин вообще;
        //
        // альтернативный вариант реализации - с помощью resultSetExtractor...

        Integer id = rs.getInt("account_id");
        boolean exists = _accounts.containsKey(id);

        if (! exists)
        {
            List<Car> cars = null;

            if ((_loadOwnedCars && rs.getInt("car_id") != 0)
                    || (! _loadOwnedCars && rs.getInt("cars_owned_count") > 0)) {
               cars = new ArrayList<>();
            }

            _accounts.put(id, new Account(
                id,
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("password"),
                cars,cars != null ? cars.size() : 0));
        }

        Account account = _accounts.get(id);

        if (account.getCars() != null && _loadOwnedCars)
        {
            Car car = new Car(
                rs.getInt("car_id"),
                null,//account,
                new CarModel(
                    rs.getInt("car_model_id"),
                    rs.getString("make"),
                    rs.getString("model")),
                rs.getString("registration_number"),
                rs.getInt("manufacture_year"),
                rs.getInt("mileage"));

           account.getCars().add(car);
        }

        return exists ? null : account;
    };

    @Override
    public List<Account> findAllByFirstName(String firstName)
    {
        _accounts.clear();

        final String sql = _loadOwnedCars ? SQL_SELECT_ALL_BY_FIRST_NAME_WITH_CARS
                                          : SQL_SELECT_ALL_BY_FIRST_NAME_2;

        return _template.query(sql/*SQL_SELECT_ALL_BY_FIRST_NAME*/, accountRowMapper, firstName);
    }
/*
    @Override  // variant 1
    public Optional<Account> find(Integer id)
    {
        _template.query(SQL_SELECT_BY_ID, accountRowMapper, id);

        if (_accounts.containsKey(id)) {
            return Optional.of(_accounts.get(id));
        }

        return Optional.empty();
    }
*/
    @Override
    public Optional<Account> find(Integer id)
    {
        final String sql = "SELECT * FROM car_portal_app.account WHERE account_id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        _templateNP.query(sql, params, accountRowMapper);

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
    /*
        @Override  // variant 2
        public void save(Account model)
        {
            try
            {
                Map<String, Object> params = new HashMap<>();

                final String sql =
                    "INSERT INTO " + "car_portal_app.account(first_name, last_name, email, password) " +
                        "VALUES (:firstName, :lastName, :email, :password)");

                params.put("firstName", model.getFirstName());
                params.put("lastName", model.getLastName());
                params.put("email", model.getEmail());
                params.put("password", model.getPasswordHash());

                _templateNP.update(sql, params);
            }
            catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    */
    @Override
    public void update(Account model) {
    }

    @Override
    public void delete(Integer id) {
    }

    @Override
    public List<Account> fetchAll()
    {
        _accounts.clear();

        if (_loadOwnedCars) {
            List<Account> rows = _template.query(SQL_SELECT_ALL_WITH_CARS, accountRowMapper);
            rows.removeAll(Collections.singletonList(null));
            return rows;
        }
        else
            return _template.query(SQL_SELECT_ALL, accountRowMapper);
    }
}
