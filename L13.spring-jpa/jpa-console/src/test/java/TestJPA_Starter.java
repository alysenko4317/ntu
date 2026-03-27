import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// https://javarush.com/groups/posts/2259-jpa--znakomstvo-s-tekhnologiey

public class TestJPA_Starter {

    // Згідно з главою "7.2.2 Obtaining an Application-managed Entity Manager" специфікації JPA
    // ми маємо використовувати EntityManagerFactory. Тому озброїмося специфікацією JPA і візьмемо
    // приклад із глави "7.3.2 Obtaining an Entity Manager Factory in a Java SE Environment" та оформимо
    // його у вигляді найпростішого Unit-тесту:
    //     https://javarush.com/groups/posts/2259-jpa--znakomstvo-s-tekhnologiey
    //
    // Якщо цей тест покаже помилку "Unrecognized JPA persistence.xml XSD version",
    // причина — у persistence.xml потрібно правильно вказати використовувану схему, як це
    // сказано у специфікації JPA в розділі "8.3 persistence.xml Schema" (правильна шапка persistence.xml)
    //
    // Крім того, важливий порядок елементів. Тому provider має бути вказаний до переліку класів.
    // Якщо цей тест виконується успішно - безпосереднє підключення до JPA ми виконали.

    @Test
    public void shouldStartHibernate() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory( "CarPortal" );
        EntityManager entityManager = emf.createEntityManager();
    }
}
