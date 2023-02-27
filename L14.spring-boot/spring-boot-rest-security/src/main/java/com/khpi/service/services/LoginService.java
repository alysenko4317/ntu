package com.khpi.service.services;

import com.khpi.service.forms.LoginForm;
import com.khpi.service.transfer.TokenDto;

public interface LoginService {
    TokenDto login(LoginForm loginForm);
}
