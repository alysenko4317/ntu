package com.khpi.mvc.dao;

import com.khpi.mvc.models.Account;
import com.khpi.mvc.models.Car;
import com.khpi.mvc.models.CarModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDAO_JdbcImpl implements CarDAO
{
    private JdbcTemplate _template;

    // language=SQL
    private static final String SQL_SELECT_ALL =
        "SELECT * FROM car_portal_app.car a " +
           "INNER JOIN car_portal_app.car_model b ON a.car_model_id = b.car_model_id " +
           "LEFT JOIN car_portal_app.car_account_link c ON c.car_id = a.car_id " +
           "LEFT JOIN car_portal_app.account d ON c.account_id = d.account_id";

    // language=SQL
    private static final String SQL_SELECT_ALL_BY_OWNER_FIRSTNAME =
        "SELECT b.*, c.*, d.* FROM car_portal_app.car_account_link a " +
            "INNER JOIN car_portal_app.car c ON a.car_id = c.car_id " +
            "INNER JOIN car_portal_app.car_model d ON c.car_model_id = d.car_model_id " +
            "INNER JOIN car_portal_app.account b ON b.account_id = a.account_id " +
            "WHERE b.first_name = ?";

    // language=SQL
    private static final String SQL_SELECT_ALL_BY_OWNER_ID =
        "SELECT b.*, c.*, d.* FROM car_portal_app.car_account_link a " +
            "INNER JOIN car_portal_app.car c ON a.car_id = c.car_id " +
            "INNER JOIN car_portal_app.car_model d ON c.car_model_id = d.car_model_id " +
            "INNER JOIN car_portal_app.account b ON b.account_id = a.account_id " +
            "WHERE b.account_id = ?";

    public CarDAO_JdbcImpl(DataSource dataSource) {
        _template = new JdbcTemplate(dataSource);
    }

    private RowMapper<Car> carRowMapper = (ResultSet rs, int i) ->
    {
        Account account = null;

        int accountId = rs.getInt("account_id");
        if (! rs.wasNull())
        {
            account = new Account(
                accountId,
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("password"), new ArrayList<Car>());

            // передаём пустой список машин, которыми владеет пользователь (new ArrayList<Car>())
            // это означает что у пользователя есть машины в собственности (в принципе),
            // но мы здесь не хотим выполнять дополнительную работу - вытягивать из БД все машины,
            // которыми пользователь потенциально может владеть, но хотябы одной (той что мы создали)
            // он точно владеет, поэтому передаём new ArrayList<Car>(), а не null;
            // причём тут null - читай коментарий к accountRowMapper в AccountDAO_JdbcImpl
        }

        return new Car(
            rs.getInt("car_id"),
            account,
            new CarModel(
                    rs.getInt("car_model_id"),
                    rs.getString("make"),
                    rs.getString("model")),
            rs.getString("registration_number"),
            rs.getInt("manufacture_year"),
            rs.getInt("mileage"));
    };

    @Override
    public Optional<Car> find(Integer id) {
        return null;
    }

    @Override
    public void update(Car model) {
    }

    @Override
    public void delete(Integer id) {
    }

    @Override
    public void save(Car model) {
    }

    @Override
    public List<Car> fetchAll() {
        return _template.query(SQL_SELECT_ALL, carRowMapper);
    }

    @Override
    public List<Car> findAllByOwner(int accountId) {
        return _template.query(SQL_SELECT_ALL_BY_OWNER_ID, carRowMapper, accountId);
    }

    @Override
    public List<Car> findAllByOwner(String firstName) {
        return _template.query(SQL_SELECT_ALL_BY_OWNER_FIRSTNAME, carRowMapper, firstName);
    }
}
