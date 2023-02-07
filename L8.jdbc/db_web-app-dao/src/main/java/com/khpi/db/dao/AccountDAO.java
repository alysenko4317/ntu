package com.khpi.db.dao;

import com.khpi.db.models.Account;

import java.util.List;

public interface AccountDAO extends CRUD<Account>
{
    List<Account> findAllByFirstName(String firstName);
}
