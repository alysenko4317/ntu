package com.khpi.mvc.dao;

import com.khpi.mvc.models.Account;

import java.util.List;

public interface AccountDAO extends CRUD<Account>
{
    List<Account> findAllByFirstName(String firstName);
}
