package com.khpi.app;

import com.khpi.mvc.dao.AccountDAO;
import com.khpi.mvc.dao.AccountDAO_JdbcImpl;
import com.khpi.mvc.models.Account;
import com.khpi.mvc.models.Car;

import javax.sql.DataSource;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        DataSource ds = DatasourceFactory.getDatasource("db.properties");

        AccountDAO dao = new AccountDAO_JdbcImpl(ds, true);
        //List<Account> accounts = dao.findAllByFirstName("Anton");
        List<Account> accounts = dao.fetchAll();

        for (Account acc: accounts)
        {
            System.out.println(acc.toString());

            final List<Car> cars = acc.getCars();
            if (cars != null) {
                for (Car car: cars)
                    System.out.println("  ---" + car.toString());
            }
        }
    }
}
