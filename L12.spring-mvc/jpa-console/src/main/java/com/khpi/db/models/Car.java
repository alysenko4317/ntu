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

    // Чтобы запомнить, какие аннотации ставить, можно запомнить, что последняя часть отвечает за поле,
    // над которым указана аннотация (ToOne - машина может являться только одной конкретной модели).
    @ManyToOne(fetch = FetchType.EAGER)
    private CarModel model;

    private String registrationNumber;
    private Integer manufactureYear;
    private Integer mileage;
}
