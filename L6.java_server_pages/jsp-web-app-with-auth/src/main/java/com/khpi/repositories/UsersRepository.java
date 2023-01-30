package com.khpi.repositories;

import com.khpi.models.User;

import java.util.List;

/**
 * UsersRepository
 * Интерфейс, описывающий поведение объекта, предоставляющего доступ к данным (паттерн DAO)
 */
public interface UsersRepository
{
    List<User> findAll();
    void save(User user);
    boolean isExist(String name, String password);
}
