package com.khpi.mvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.khpi.mvc.models.Car;


import java.util.List;

public interface CarsRepository
    extends JpaRepository<Car, Integer>
{
    // внимание, МАГИЯ! снова используется имя метода, на этот раз строится запрос
    // к СУБД с джойном, так как FirstName нужно смотреть в таблице accounts, а машины
    // мы выбираем из таблицы car; более того, так как связь между сущностями
    // Account->Car типа many-to-many то есть ещё промежуточная таблица account_car_link,
    // которая также участвует в запросе. Обратите внимание на знак _
    // Именно такой синтаксис инструктирует JPA использовать подсущности в запросе.
    //@Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    List<Car> findAllByOwners_FirstName(String firstNameOwner);

 //   @Query(nativeQuery = true,
 //          value = "SELECT * FROM car INNER JOIN car_model WHERE model = ?1;")
 //   List<Car> findAllByModel(String model);
}
