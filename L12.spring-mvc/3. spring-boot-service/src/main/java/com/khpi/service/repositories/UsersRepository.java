package com.khpi.service.repositories;

import com.khpi.service.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Account, Integer> {
    List<Account> findAllByFirstName(String firstName);
    Optional<Account> findOneByEmail(String login);
}
