package com.khpi.mvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.khpi.mvc.models.Car;


import java.util.List;

public interface CarsRepository
    extends JpaRepository<Car, Integer>
{
    // увага, МАГІЯ! знову використовується ім'я методу, цього разу будується запит
    // до СУБД з джойном, бо FirstName потрібно дивитися в таблиці accounts, а машини
    // ми вибираємо з таблиці car; більш того, оскільки зв'язок між сутностями
    // Account->Car типу many-to-many, тобто ще є проміжна таблиця account_car_link,
    // яка також бере участь у запиті. Зверніть увагу на знак _
    // Саме такий синтаксис інструктує JPA використовувати підсутності в запиті.
    //@Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    List<Car> findAllByOwners_FirstName(String firstNameOwner);

 //   @Query(nativeQuery = true,
 //          value = "SELECT * FROM car INNER JOIN car_model WHERE model = ?1;")
 //   List<Car> findAllByModel(String model);
}
