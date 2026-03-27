package com.khpi.mvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.khpi.mvc.models.Account;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersRepository
    extends JpaRepository<Account, Integer>
{
    // ось тут ВЗАГАЛІ магія! назва методу використовується для побудови
    // запиту до СУБД з фільтрацією за полем (firstName).
    List<Account> findAllByFirstName(String firstName);

    // використання custom queries
    @Query(nativeQuery = true, value =
        "SELECT a.*, COUNT(b.account_id) AS cars_owned_count FROM car_portal_app.account a " +
            "LEFT JOIN car_portal_app.car_account_link b ON a.account_id = b.account_id " +
            "GROUP BY a.account_id, a.first_name " +
            "ORDER BY a.first_name")
    List<Account> findAllWithOwnersCount();
}
