package com.khpi.repositories;

import com.khpi.models.User;

import java.util.List;

/**
 * UsersRepository
 * Інтерфейс, що описує поведінку об'єкта, який надає доступ до даних (патерн DAO)
 */
public interface UsersRepository
{
    List<User> findAll();
    void save(User user);
    boolean isExist(String name, String password);
}
