
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// https://javarush.com/groups/posts/2259-jpa--znakomstvo-s-tekhnologiey

public class TestJPA_Starter {

    // Согласно главе "7.2.2 Obtaining an Application-managed Entity Manager" спецификации JPA
    // мы должны использовать EntityManagerFactory. Поэтому, вооружимся спецификацией JPA и возьмём
    // пример из главы "7.3.2 Obtaining an Entity Manager Factory in a Java SE Environment" и оформим
    // его в виде простейшего Unit теста:
    //     https://javarush.com/groups/posts/2259-jpa--znakomstvo-s-tekhnologiey
    //
    // Если этот тест покажет ошибку "Unrecognized JPA persistence.xml XSD version",
    // причина — в persistence.xml нужно правильно указать используемую схему, как это
    // сказано в спецификации JPA в разделе "8.3 persistence.xml Schema" (правильная шапка persistence.xml)
    //
    // Кроме того, важен порядок элементов. Поэтому, provider должен быть указан до перечисления классов.
    // Если этот тест выполняется успешно - Непосредственное подключение к JPA мы выполнили.

    @Test
    public void shouldStartHibernate() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory( "CarPortal" );
        EntityManager entityManager = emf.createEntityManager();
    }
}
