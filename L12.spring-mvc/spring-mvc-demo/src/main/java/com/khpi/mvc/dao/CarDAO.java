package com.khpi.mvc.dao;

import com.khpi.mvc.models.Car;

import java.util.List;

public interface CarDAO extends CRUD<Car>
{
    List<Car> findAllByOwner(int accountId);
    List<Car> findAllByOwner(String firstName);
}
