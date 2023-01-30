package com.khpi.mocks;

import com.khpi.models.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, реализующий паттеррн Singleton
 * Представляет собой InMemory-хранилище для получения информации о зарегистрированных пользователях
 */
public class InMemoryStorage
{
    // переменная, которая хранит ссылку на единственный экземпляр объекта класса FakeStorage
    private static final InMemoryStorage storage;

    // статически инициализатор, создающий объект класса FakeStorage;
    // вызывается один раз при загрузке класса в JVM
    static {
        storage = new InMemoryStorage();
    }

    // метод, предоставляющий доступ к объекту класса
    public static InMemoryStorage getInstance() {
        return storage;
    }

    // приватный констуктор, выполняющий инициализацию списка
    private InMemoryStorage()
    {
        this.users = new ArrayList<>();
        users.add(new User("admin", "123", LocalDate.parse("1994-02-02")));
        users.add(new User("John Salivan", "qwerty007", LocalDate.parse("1994-02-02")));
        users.add(new User("Simona Jason", "qwerty007", LocalDate.parse("1994-02-02")));
        users.add(new User("Bruce Willis", "qwerty007", LocalDate.parse("1994-02-02")));
    }

    // метод, возвращающий список пользователей
    public List<User> users() {
        return users;
    }

    // поле-список, хранящее список пользователей системы
    private List<User> users;
}
