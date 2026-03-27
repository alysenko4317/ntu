package com.khpi.db.models;

public class Car
{
    public Car(final Integer id,
               final Account owner,
               final CarModel model,
               final String registrationNumber,
               final Integer manufactureYear,
               final Integer mileage)
    {
        _pk = id;
        _model = model;
        _owner = owner;
        _registrationNumber = registrationNumber;
        _manufactureYear = manufactureYear;
        _mileage = mileage;
    }

    public CarModel getModel() {
        return _model;
    }

    public Account getOwner() {
        return _owner;
    }

    public String getRegistrationNumber() {
        return _registrationNumber;
    }

    public String getManufactureYear() {
        return _registrationNumber;
    }

    public String getMileage() {
        return _registrationNumber;
    }

    private Integer _pk;
    private CarModel _model;
    private Account _owner;
    private String _registrationNumber;
    private Integer _manufactureYear;
    private Integer _mileage;
}
