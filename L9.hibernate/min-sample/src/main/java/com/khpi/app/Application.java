package com.khpi.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Application
{
    public static void main(String[] args)
    {
        Configuration configuration = new Configuration();

        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5433/car_portal");
        configuration.setProperty("hibernate.connection.username", "postgres");
        configuration.setProperty("hibernate.connection.password", "1234");
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.setProperty("hibernate.show_sql", "true");

        //configuration.addResource("User.hbm.xml");
        //configuration.addAnnotatedClass(Car.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        doStuff(session);

        session.close();
        sessionFactory.close();
    }

    private static void doStuff(Session session) {
        // TODO: Implement models and this method
    }
}
