package com.khpi.db.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude="owner")   // avoid stack overflow
@Entity
public class Car
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

    @ManyToMany(mappedBy = "cars")
    private Set<Account> owners = new HashSet<>(0);

    // Щоб запам'ятати, які анотації ставити, можна запам'ятати, що остання частина відповідає за поле,
    // над яким вказана анотація (ToOne - машина може належати лише одній конкретній моделі).
    @ManyToOne(fetch = FetchType.EAGER)
    private CarModel model;

    private String registrationNumber;
    private Integer manufactureYear;
    private Integer mileage;
}
