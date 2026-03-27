package com.khpi.models.builders;

import com.khpi.models.Account;

public class AccountBuilder
{
    private Account _account;

    public AccountBuilder() {
        _account = new Account();
    }
    public AccountBuilder(Integer id) {
        _account = new Account(id);
    }

    public Account build() {
        return _account;
    }

    public AccountBuilder setFirstName(final String firstName) {
        _account.setFirstName(firstName); return this;
    }
    public AccountBuilder setLastName(final String lastName) {
        _account.setLastName(lastName); return this;
    }
    public AccountBuilder setPasswordHash(final String passwordHash) {
        _account.setPasswordHash(passwordHash); return this;
    }
    public AccountBuilder setEmail(String email) {
        _account.setEmail(email); return this;
    }
}
