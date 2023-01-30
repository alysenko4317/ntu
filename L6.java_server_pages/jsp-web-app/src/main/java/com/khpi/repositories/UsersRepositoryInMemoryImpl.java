package com.khpi.repositories;

import com.khpi.mocks.InMemoryStorage;
import com.khpi.models.User;

import java.util.List;

/**
 * UsersRepositoryInMemoryImpl
 * Реализация объекта доступа к данным с испольованием фейкового хранилища
 */
public class UsersRepositoryInMemoryImpl implements UsersRepository
{
    public List<User> findAll() {
        return InMemoryStorage.getInstance().getAllUsers();
    }
}
