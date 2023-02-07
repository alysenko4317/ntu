package com.khpi.db.dao;

import com.khpi.db.models.Car;

import java.util.List;

public interface CarDAO extends CRUD<Car>
{
    List<Car> findAllByOwner(int accountId);
    List<Car> findAllByOwner(String firstName);
}
