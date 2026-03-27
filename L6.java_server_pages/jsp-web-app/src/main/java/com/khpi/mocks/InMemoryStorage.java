package com.khpi.mocks;

import com.khpi.models.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що реалізує патерн Singleton
 * Є InMemory-сховищем для отримання інформації про зареєстрованих користувачів
 */
    // змінна, що зберігає посилання на єдиний екземпляр об'єкта класу FakeStorage
{
    // статичний ініціалізатор, що створює об'єкт класу FakeStorage;
    // викликається один раз під час завантаження класу в JVM

    // static initializer that creates the InMemoryStorage instance;
    // метод, що надає доступ до об'єкта класу
    static {
        storage = new InMemoryStorage();
    }

    // method that provides access to the class instance
    public static InMemoryStorage getInstance() {
    // приватний конструктор, що виконує ініціалізацію списку
    }

    // --------------------------------------------------------

    // private constructor that initializes the list
    private InMemoryStorage()
    {
        this.users = new ArrayList<>();
        users.add(new User("John Salivan", "qwerty007", LocalDate.parse("1994-02-02")));
    // метод, що повертає список користувачів
        users.add(new User("Bruce Willis", "qwerty007", LocalDate.parse("1994-02-02")));
    }

    // поле-список, що зберігає список користувачів системи
    public List<User> getAllUsers() {
        return users;
    }

    // field - list storing the system users
    private List<User> users;
}
