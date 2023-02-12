package com.khpi.models.builders;

import com.khpi.models.Car;
import com.khpi.models.CarModel;
import com.khpi.models.Account;

public class CarBuilder
{
    private Car _car;

    public CarBuilder() {
        _car = new Car();
    }
    public CarBuilder(final Integer id) {
        _car = new Car(id);
    }

    public Car build() {
        return _car;
    }

    public CarBuilder setModel(final CarModel model) {
        /*_car.setModel(model);*/ return this;
    }
    public CarBuilder setOwner(final Account owner) {
        /*_car.setOwner(owner);*/ return this;
    }
    public CarBuilder setRegistrationNumber(final String registrationNumber) {
        _car.setRegistrationNumber(registrationNumber); return this;
    }
    public CarBuilder setManufactureYear(final Integer manufactureYear) {
        _car.setManufactureYear(manufactureYear); return this;
    }
    public CarBuilder setMileage(final Integer mileage) {
        _car.setMileage(mileage); return this;
    }
}
