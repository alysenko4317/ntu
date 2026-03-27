package com.khpi.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(value = "com.khpi.mvc")
@EnableJpaRepositories(basePackages = "com.khpi.mvc.repositories")
@EnableTransactionManagement
public class JpaConfiguration
{
    @Autowired
    private DataSource dataSource;
    //EntityManager em;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory()
    {
        // такой EntityManager об'єднає Spring та Hibernate
        LocalContainerEntityManagerFactoryBean emf =
             new LocalContainerEntityManagerFactoryBean();

        emf.setDataSource(dataSource);  // 1. datasource
        emf.setPackagesToScan("com.khpi.mvc.models");  // 2. where models located

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();  // 3. a layer between jpa and hibernate
        adapter.setDatabase(Database.POSTGRESQL);  // DBMS type

        emf.setJpaVendorAdapter(adapter);
        emf.setJpaProperties(jpaProperties());

        return emf;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory)
    {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    public Properties jpaProperties()
    {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.default_schema", "car_portal_app");
        return properties;
    }
}
