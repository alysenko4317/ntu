import com.khpi.db.models.Account;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

import static org.junit.Assert.assertNotNull;

// https://javarush.com/groups/posts/2259-jpa--znakomstvo-s-tekhnologiey

public class TestJPA_EM_T {

    // Згідно з главою "7.2.2 Obtaining an Application-managed Entity Manager" специфікації JPA
    // ми маємо використовувати EntityManagerFactory. Тому озброїмося специфікацією JPA і візьмемо
    // приклад із глави "7.3.2 Obtaining an Entity Manager Factory in a Java SE Environment" та оформимо
    // його у вигляді найпростішого Unit-тесту:
    // Якщо цей тест покаже помилку "Unrecognized JPA persistence.xml XSD version",
    // причина — у persistence.xml потрібно правильно вказати використовувану схему, як це
    // сказано у специфікації JPA в розділі "8.3 persistence.xml Schema" (правильна шапка persistence.xml)
    // Крім того, важливий порядок елементів. Тому provider має бути вказаний до переліку класів.
    // Якщо цей тест виконується успішно - безпосереднє підключення до JPA ми виконали.
    // If this test passes successfully - the direct JPA connection has been established.

    //@Test
    public void shouldStartHibernate() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory( "CarPortal" );
        EntityManager entityManager = emf.createEntityManager();
    }

    // Перш ніж рухатися далі, подумаємо про інші тести. Кожен наш тест буде
    // вимагати EntityManager. Зробімо так, щоб у кожного тесту був свій EntityManager
    // на початок виконання. Крім того, ми хочемо, щоб БД щоразу була новою. Завдяки тому,
    // що ми використовуємо inmemory-варіант, достатньо закривати EntityManagerFactory.
    // Створення Factory — дорога операція. Але для тестів — це виправдано. JUnit дозволяє задати
    // методи, які виконуватимуться до (Before) і після (After) виконання кожного тесту.
    // Тепер перед виконанням будь-якого тесту буде створено нову EntityManagerFactory,
    // що призведе до створення нової БД, оскільки hibernate.hbm2ddl.auto має значення create.
    // А з нової фабрики отримаємо новий EntityManager.
    // A new EntityManager will be obtained from the new factory.

    private EntityManager _em;

    @Before
    public void init()
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory( "CarPortal" );
        _em = emf.createEntityManager();
        _em.getTransaction().begin();
    }

    @After
    public void close()
    {
        if (_em.getTransaction().isActive()) {
            _em.getTransaction().commit();
        }
        _em.getEntityManagerFactory().close();
        _em.close();
    }

    @Test
    public void shouldPersistCategory()
    {
        Account acc = new Account();
        // Ми додали керування транзакціями в наш код, який виконується до тестів і після.
        // Тепер зміни, накопичені в EntityManager, за допомогою транзакції будуть закомічені
        // (підтверджені та збережені) у БД.
        // (confirmed and saved) to the DB via a transaction.
        _em.flush();
    }

    @Test
        // спробуємо тепер знайти нашу сутність; це тест на пошук сутності за її ID:
        // спробуємо тепер знайти нашу сутність; це тест на пошук сутності за її ID:
        Account acc = new Account();
        acc.builder().firstName("Anton").lastName("Lysenko").email("s@s.s").passwordHash("123").build();
        _em.persist(acc);
        // У цьому випадку ми отримаємо раніше збережену нами сутність, але в логу не побачимо SELECT-запитів.
        assertNotNull(result);

        // А все тому, що ми кажемо: "Менеджер сутностей, знайди, будь ласка, мені сутність Category з ID=1".
        // А менеджер сутностей спочатку дивиться у своєму контексті (використовує його як кеш), і лише
        // якщо не знаходить — іде шукати в БД. Варто змінити ID на 2 (такого немає, ми зберегли лише один
        // екземпляр), як ми побачимо, що SELECT-запит з'являється. Бо в контексті не знайдено сутностей.
        // Існують різні команди, якими ми можемо керувати станом сутності в контексті.
        // Перехід сутності з одного стану в інший називається життєвим циклом сутності — lifecycle.
        // was not found in the context and the EntityManager tries to find it in the DB.
        //
        // 1. New або Transient (тимчасовий)
        // The transition of an entity from one state to another is called the entity lifecycle.
    }

    @Test
    public void entityLifecycle()
    {
        if (_em.getTransaction().isActive()) {
            _em.getTransaction().commit();
        }

        // 2. Managed або Persistent
        // 3. Транзакцію завершено, усі сутності в контексті detached
            .firstName("Anton")
            .lastName("Lysenko")
            .email("s@s.s")
        // 4. Сутність вилучаємо з контексту, вона стає detached

        // 2. Managed or Persistent
        // 5. Сутність із detached можна знову зробити managed

        // 6. І можна зробити Removed. Цікаво, що acc все одно detached
        _em.getTransaction().begin();
        _em.getTransaction().commit();

        // 4. Remove entity from context - it becomes detached
        _em.detach(acc);

        // 5. A detached entity can be made managed again
        Account managed = _em.merge(acc);

        // 6. And it can be made Removed. Note that acc remains detached
        _em.remove(managed);
    }

    @Test
    public void shouldPerformQuery()
    {
        Account acc = new Account().builder()
            .firstName("Anton")
            .lastName("Lysenko")
            .email("s@s.s")
            .passwordHash("123").build();

        _em.persist(acc);

        Query query = _em.createQuery(
            "SELECT c from Account c WHERE c.firstName = 'Anton'");
        // Використання CriteriaAPI для побудови запиту до СУБД
        // Цей приклад рівносильний виконанню запиту "SELECT c FROM Category c".
        Account acc = new Account().builder()
            .firstName("Anton")
            .lastName("Lysenko")
            .email("s@s.s")
            .passwordHash("123").build();

        _em.persist(acc);

        // Using the Criteria API to build a query to the DB.
        // This example is equivalent to running "SELECT c FROM Account c".

        CriteriaBuilder cb = _em.getCriteriaBuilder();
        CriteriaQuery<Account> query = cb.createQuery(Account.class);
        Root<Account> c = query.from(Account.class);
        query.select(c);
        List<Account> resultList = _em.createQuery(query).getResultList();
        Assert.assertEquals(1, resultList.size());
    }
}
