package com.khpi.service.controllers;

import com.khpi.service.transfer.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.khpi.service.forms.LoginForm;
import com.khpi.service.services.LoginService;
import com.khpi.service.transfer.TokenDto;

import java.util.List;

import static com.khpi.service.transfer.UserDto.from;


@RestController
public class LoginController
{
    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public ResponseEntity<Object> stub() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody  LoginForm loginForm) {
        return ResponseEntity.ok(loginService.login(loginForm));
    }
}
