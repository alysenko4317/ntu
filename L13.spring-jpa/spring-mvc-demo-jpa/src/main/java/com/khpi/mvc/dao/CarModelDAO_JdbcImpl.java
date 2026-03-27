package com.khpi.mvc.dao;

import com.khpi.mvc.models.Account;
import com.khpi.mvc.models.CarModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class CarModelDAO_JdbcImpl implements CRUD<CarModel>
{
    private JdbcTemplate _template;

    // language=SQL
    private static final String SQL_SELECT_ALL =
        "SELECT * FROM car_portal_app.car_model ORDER BY make";

    public CarModelDAO_JdbcImpl(DataSource dataSource) {
        _template = new JdbcTemplate(dataSource);
    }

    private RowMapper<CarModel> carModelRowMapper = (ResultSet rs, int i) -> {
        return new CarModel(
            rs.getInt("car_model_id"),
            rs.getString("make"),
            rs.getString("model"));
    };

    @Override
    public Optional<CarModel> find(Integer id) {
        return null;
    }

    @Override
    public void update(CarModel model) {
    }

    @Override
    public void delete(Integer id) {
    }

    @Override
    public void save(CarModel model) {
    }

    @Override
    public List<CarModel> fetchAll() {
        return _template.query(SQL_SELECT_ALL, carModelRowMapper);
    }
}
