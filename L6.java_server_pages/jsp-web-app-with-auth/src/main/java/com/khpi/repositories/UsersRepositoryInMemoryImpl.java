package com.khpi.repositories;

import com.khpi.mocks.InMemoryStorage;
import com.khpi.models.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * UsersRepositoryInMemoryImpl
 * Реализация объекта доступа к данным с испольованием фейкового хранилища
 */
public class UsersRepositoryInMemoryImpl implements UsersRepository
{
    public List<User> findAll() {
        return InMemoryStorage.getInstance().users();
    }

    @Override
    public void save(User user) {
        InMemoryStorage.getInstance().users().add(user);
    }

    @Override
    public boolean isExist(String name, String password)
    {
        for (User user : InMemoryStorage.getInstance().users())
        {
            if (user.getName().equals(name) && user.getPassword().equals(password))
                return true;
        }

        return false;
    }
}
