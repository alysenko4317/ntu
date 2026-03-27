package com.khpi.repositories;

import com.khpi.mocks.InMemoryStorage;
import com.khpi.models.User;

import java.util.List;

/**
 * UsersRepositoryInMemoryImpl
 * Реалізація об'єкта доступу до даних із використанням фейкового сховища
 */
public class UsersRepositoryInMemoryImpl implements UsersRepository
{
    public List<User> findAll() {
        return InMemoryStorage.getInstance().getAllUsers();
    }
}
