package com.khpi.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="car")
public class Car
{
    @Id()
    @Column(name = "car_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer _pk;

    @ManyToOne
    @JoinColumn(name = "car_model_id")
    //@JoinTable(name = "car_model", joinColumns = @JoinColumn( name = "car_model_id", nullable=false ))
    private CarModel _model;

    @ManyToMany(mappedBy = "cars")
    private Set<Account> accounts = new HashSet<>(0);

    @Column(name = "registration_number")
    private String _registrationNumber;

    @Column(name = "manufacture_year")
    private Integer _manufactureYear;

    @Column(name = "mileage")
    private Integer _mileage;

    public Car() { }
    public Car(final Integer id) {
        _pk = id;
    }

    public Integer getId() {
        return _pk;
    }
    public CarModel getModel() {
        return _model;
    }
    public Set<Account> getOwners() {
        return accounts;
    }
    public String getRegistrationNumber() {
        return _registrationNumber;
    }
    public Integer getManufactureYear() {
        return _manufactureYear;
    }
    public Integer getMileage() {
        return _mileage;
    }

    public void setId(Integer _pk) {
        _pk = _pk;
    }
    public void setModel(CarModel model) {
        _model = model;
    }
    public void setRegistrationNumber(String registrationNumber) {
        _registrationNumber = _registrationNumber;
    }
    public void setManufactureYear(Integer manufactureYear) {
        _manufactureYear = _manufactureYear;
    }
    public void setMileage(Integer mileage) {
        _mileage = mileage;
    }

    public void addOwner(Account owner) {
        accounts.add(owner);
    }

    @Override
    public String toString() {
        return "Car {" +
            "_pk=" + _pk +
            ", _registrationNumber='" + _registrationNumber + '\'' +
            ", _manufactureYear=" + _manufactureYear +
            ", _mileage=" + _mileage +
            ", model=" + (_model != null ? _model.getName() : null) +
            '}';
    }
}
