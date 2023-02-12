package com.khpi.app;

import com.khpi.models.Account;
import com.khpi.models.Car;
import com.khpi.models.CarModel;
import com.khpi.models.builders.AccountBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Application
{
    private static void configure_hibernate(Configuration cfg)
    {
        // DBMS connection setup
        cfg.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5433/car_portal");
        cfg.setProperty("hibernate.connection.username", "postgres");
        cfg.setProperty("hibernate.connection.password", "1234");
        cfg.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");

        // JDBC connection pool (use the built-in) --> not for production
        cfg.setProperty("connection.pool_size", "1");

        // default_schema is important important to be specified for hbm2ddl.auto works correctly
        cfg.setProperty("hibernate.default_schema", "car_portal_app");
        cfg.setProperty("hibernate.hbm2ddl.auto", "update");

        // Disable the second-level cache
        cfg.setProperty("cache.provider_class", "org.hibernate.cache.internal.NoCacheProvider");

        // logging and output options
        cfg.setProperty("hibernate.show_sql", "true");
        cfg.setProperty("format_sql", "true");
        cfg.setProperty("use_sql_comments", "true");

        // models mapping setup
        cfg.addResource("account.hbm.xml");
        cfg.addAnnotatedClass(Car.class);
        cfg.addAnnotatedClass(CarModel.class);
    }

    public static void main(String[] args)
    {
        Configuration configuration = new Configuration();

        // we can configure through .xml file - prefered way
        configuration.configure("hibernate.cfg.xml");

        // or we can configure directly in code - not good for production
        // configure_hibernate(configuration);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        doStuff(session);

        session.close();
        sessionFactory.close();
    }

    private static void doStuff(Session session)
    {
        //-------------------------------------------------------
        // query for the particular user with id == ?
        //-------------------------------------------------------

        Account user = session.createQuery(
            "from Account account where account.id = 485", Account.class).getSingleResult();

        System.out.println(user);
        for (Car car : user.getCars())
             System.out.println("  -- " + car.toString());

        //-------------------------------------------------------
        // creat new user and save it into the database
        //-------------------------------------------------------

        session.beginTransaction();

        final Account account =
            new AccountBuilder().setFirstName("Anton")
                                .setLastName("Lysenko")
                                .setEmail("alysenko@gmail.com")
                                .setPasswordHash("xxxxxxxxxxxxxxxxxxxxxx")
                                .build();

        System.out.println(account.toString());
        // session.save(account);
        session.getTransaction().commit();
        System.out.println(account.toString());

        //-------------------------------------------------------
        // query for all of cars models registered
        //-------------------------------------------------------

        List<CarModel> models =
            session.createQuery("from CarModel carModel", CarModel.class).getResultList();

        //-------------------------------------------------------
        // query for all of cars registered
        //-------------------------------------------------------

        List<Car> cars = session.createQuery("from Car car", Car.class).getResultList();
        for (Car car : cars)
            System.out.println(car.toString());
    }
}
