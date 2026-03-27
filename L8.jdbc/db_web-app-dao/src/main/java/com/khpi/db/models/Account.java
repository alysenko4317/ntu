package com.khpi.db.models;

public class Account
{
    public Account(Integer pk,
                   final String firstName,
                   final String lastName,
                   final String email,
                   final String password)
    {
        _pk = pk;
        _firstName = firstName;
        _lastName = lastName;
        _email = email;
        _passwordHash = password;
    }

    public Account(final String firstName, final String lastName, final String email, final String password)
    {
        this(null, firstName, lastName, email, password);
    }

    // getters are required by JSP

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

    private Integer _pk;
    private String _firstName;
    private String _lastName;
    private String _email;
    private String _passwordHash;
}
