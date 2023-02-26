package com.khpi.service.services;

import com.khpi.service.forms.UserForm;
import com.khpi.service.models.Account;

import java.util.List;

public interface UsersService
{
    void signUp(UserForm userForm);
    List<Account> findAll();
    Account findOne(Integer userId);
}
