
import com.khpi.db.models.Account;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// https://javarush.com/groups/posts/2259-jpa--znakomstvo-s-tekhnologiey

public class TestJPA_EM
{
    // Прежде чем мы будем двигаться дальше, подумаем про остальные тесты. Каждый наш тест будет
    // требовать EntityManager. Давайте сделаем так, чтобы у каждого теста был свой EntityManager
    // на начало выполнения. Кроме того, мы хотим чтобы БД каждый раз была новая. Благодаря тому,
    // что мы используем inmemory вариант, достаточно закрывать EntityManagerFactory.
    //
    // Создание Factory — дорогая операция. Но для тестов — это оправдано. JUnit позволяет задать
    // методы, которые будут выполняться перед (Before) и после (After) выполнением каждого теста.
    //
    // Теперь, перед выполнением любого теста будет создана новая EntityManagerFactory,
    // что повлечёт за собой создание новой БД, т.к. hibernate.hbm2ddl.auto имеет значение create.
    // А из новой фабрики получим новый EntityManager.

    private EntityManager _em;

    @Before
    public void init() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory( "CarPortal" );
        _em = emf.createEntityManager();
    }

    @After
    public void close() {
        _em.getEntityManagerFactory().close();
        _em.close();
    }

    @Test
    public void shouldPersistCategory() {
        Account acc = new Account().builder()
            .firstName("Anton")
            .lastName("Lysenko")
            .email("s@s.s")
            .passwordHash("123").build();
        _em.persist(acc);

        // При выполнении теста мы видим в том числе то, какие запросы отправляются в базу
        // (благодаря опции hibernate.show_sql). Но при сохранении мы не видим никаких insert'ов.
        // Получается, что мы на самом деле ничего не сохранили?
        //
        // JPA позволяет синхронизировать контекст персистенции и БД при помощи метода flush:
        //
        // _em.flush();
    }
}
