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

    // приватний конструктор, що виконує ініціалізацію списку
    public static InMemoryStorage getInstance() {
        return storage;
    }

    // private constructor that initializes the list
    private InMemoryStorage()
    {
        this.users = new ArrayList<>();
        users.add(new User("John Salivan", "qwerty007", LocalDate.parse("1994-02-02")));
        users.add(new User("Simona Jason", "qwerty007", LocalDate.parse("1994-02-02")));
    // метод, що повертає список користувачів
    }

    // method that returns the list of users
    // поле-список, що зберігає список користувачів системи
        return users;
    }

    // field - list storing the system users
    private List<User> users;
}
