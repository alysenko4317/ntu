package com.khpi.service.app;

public interface PasswordEncoder {
    String encode(String password);
    boolean matches(String p1, String p2);
}
