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
    // Перш ніж рухатися далі, подумаємо про інші тести. Кожен наш тест буде
    // вимагати EntityManager. Зробімо так, щоб у кожного тесту був свій EntityManager
    // на початок виконання. Крім того, ми хочемо, щоб БД щоразу була новою.
    // Створення Factory — дорога операція. Але для тестів — це виправдано. JUnit дозволяє задати
    // методи, які виконуватимуться до (Before) і після (After) виконання кожного тесту.
    // Тепер перед виконанням будь-якого тесту буде створено нову EntityManagerFactory,
    // що призведе до створення нової БД, оскільки hibernate.hbm2ddl.auto має значення create.
    // А з нової фабрики отримаємо новий EntityManager.
    // A new EntityManager will be obtained from the new factory.

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
        // Під час виконання тесту ми бачимо, зокрема, які запити відправляються до бази
        // (завдяки опції hibernate.show_sql). Але під час збереження ми не бачимо жодних insert.
        // Виходить, що ми насправді нічого не зберегли?
        // JPA дозволяє синхронізувати контекст персистентності та БД за допомогою методу flush:
        //
        // JPA allows synchronizing the persistence context with the DB using the flush method:
        //
        _em.flush();
    }
}
