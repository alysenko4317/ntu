package com.khpi.service.security.details;

import com.khpi.service.models.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

// UserDetails - представляет собой адаптер "пользователя" для Spring-a

public class UserDetailsImpl implements UserDetails {

    private Account user;

    public UserDetailsImpl(Account user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        // що може користувач робити у системі ?
        String userRole = user.getRole().name();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole);
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return user.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
       // return !user.getState().equals(Account.State.BANNED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
      //  return user.getState().equals(Account.State.ACTIVE);
    }

    public Account getUser() {
        return user;
    }
}
