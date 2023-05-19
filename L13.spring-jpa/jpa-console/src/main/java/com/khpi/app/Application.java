package com.khpi.app;

import com.khpi.db.models.Account;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// https://javarush.com/groups/posts/2259-jpa--znakomstvo-s-tekhnologiey

public class Application
{
    private static EntityManager em;

    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CarPortal");
        em = emf.createEntityManager();
        createAccount("_test", "_test", "_test", "123");
       // createStudent(2, "Likitha", "Manjunatha", 98);
       // createStudent(3, "Advith", "Tyagraj", 99);
    }

    private static void createAccount(String firstName, String lastName, String email, String pwd)
    {
        em.getTransaction().begin();
        Account acc = new Account().builder()
                                   .firstName(firstName)
                                   .lastName(lastName)
                                   .email(email)
                                   .passwordHash(pwd)
                                   .build();
        em.persist(acc);
        em.getTransaction().commit();
    }
}
