package com.khpi.db.models;

public class CarModel
{
    public CarModel(Integer id, String make, String model) {
        _pk = id;
        _model = model;
        _make = make;
    }

    public String getName() {
        return _make + " " + _model;
    }

    private Integer _pk;
    private String _model;
    private String _make;
}
