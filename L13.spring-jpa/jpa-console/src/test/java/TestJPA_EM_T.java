
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

    //@Test
    public void shouldStartHibernate() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory( "CarPortal" );
        EntityManager entityManager = emf.createEntityManager();
    }

    // ---------------------------------------------------------------------------------

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
        acc.builder().firstName("Anton").lastName("Lysenko").email("s@s.s").passwordHash("123").build();

        _em.persist(acc);

        // Мы добавили управление транзакциями в наш код, который выполняется до тестов и после.
        // Теперь изменения, накопленные в EntityManager будут при помощи транзакции закоммичены
        // (подтверждены и сохранены) в БД.
        _em.flush();
    }

    @Test
    public void shouldFindCategory() {
        // попробуем теперь найти нашу сущность; это тест на поиск сущности по её ID:
        Account acc = new Account();
        acc.builder().firstName("Anton").lastName("Lysenko").email("s@s.s").passwordHash("123").build();
        _em.persist(acc);
        Account result = _em.find(Account.class, 1);
        assertNotNull(result);

        // В этом случае мы получим ранее сохранённую нами сущность, но в логе мы не увидим SELECT запросов.
        //
        // А всё по тому, что мы говорим: "Менеджер сущностей, найди пожалуйста мне сущность Категория с ID=1".
        // А менеджер сущностей сначала смотрит у себя в контексте (использует его своего рода кэш), и только
        // если не находит — идёт искать в БД. Стоит изменить ID на 2 (такого нет, мы сохранили только один
        // экземпляр), как мы увидим, что SELECT запрос появляется. Потому что в контексте не найдено сущностей
        // и EntityManager пытается найти сущность БД.
        //
        // Существуют разные комманды, которыми мы можем управлять состоянием сущности в контексте.
        // Переход сущности из одного состояния в другое называется жизненным циклом сущности — lifecycle.
    }

    @Test
    public void entityLifecycle()
    {
        if (_em.getTransaction().isActive()) {
            _em.getTransaction().commit();
        }

        // 1. New или Transient (временный)
        Account acc = new Account().builder()
            .firstName("Anton")
            .lastName("Lysenko")
            .email("s@s.s")
            .passwordHash("123").build();

        // 2. Managed или Persistent
        _em.persist(acc);

        // 3. Транзакция завершена, все сущности в контексте detached
        _em.getTransaction().begin();
        _em.getTransaction().commit();

        // 4. Сущность изымаем из контекста, она становится detached
        _em.detach(acc);

        // 5. Сущность из detached можно снова сделать managed
        Account managed = _em.merge(acc);

        // 6. И можно сделать Removed. Интересно, что acc всё равно detached
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
        assertNotNull(query.getSingleResult());
    }

    @Test
    public void shouldFindWithCriteriaAPI() {
        Account acc = new Account().builder()
            .firstName("Anton")
            .lastName("Lysenko")
            .email("s@s.s")
            .passwordHash("123").build();

        _em.persist(acc);

        // Использование CriteriaAPI для построения запроса к СУБД
        // Данный пример равносилен выполнению запроса "SELECT c FROM Category c".

        CriteriaBuilder cb = _em.getCriteriaBuilder();
        CriteriaQuery<Account> query = cb.createQuery(Account.class);
        Root<Account> c = query.from(Account.class);
        query.select(c);
        List<Account> resultList = _em.createQuery(query).getResultList();
        Assert.assertEquals(1, resultList.size());
    }
}
