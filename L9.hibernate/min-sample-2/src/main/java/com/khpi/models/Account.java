package com.khpi.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Account
{
    private Integer _pk;
    private String _firstName;
    private String _lastName;
    private String _email;
    private String _passwordHash;
    private LocalDate _loginDate;
    private Set<Car> cars = new HashSet<>(0);

    public Account( ) { }
    public Account(Integer id) {
        _pk = id;
    }

    // getters are required by JSP

    public Integer getId() {
        return _pk;
    }
    public String getFirstName() {
        return _firstName;
    }
    public String getLastName() {
        return _lastName;
    }
    public String getEmail() {
        return _email;
    }
    public String getPasswordHash() {
        return _passwordHash;
    }
    public LocalDate getLoginDate() {
        return _loginDate;
    }
    public Set<Car> getCars() {
        return cars;
    }

    public void setId(final Integer id) {
        _pk = id;
    }
    public void setFirstName(final String firstName) {
        _firstName = firstName;
    }
    public void setLastName(final String lastName) {
        _lastName = lastName;
    }
    public void setEmail(final String email) {
        _email = email;
    }
    public void setPasswordHash(final String passwordHash) {
        _passwordHash = passwordHash;
    }
    public void setLoginDate(final LocalDate loginDate) {
        _loginDate = loginDate;
    }
    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "Account [id=" + _pk
            + ", email=" + _email
            + ", firstName=" + _firstName
            + ", lastName=" + _lastName
            + ", password=" + _passwordHash
            + ", cars_owned=" + cars.size() +"]";
    }
}
