package com.khpi.mvc.models;

import lombok.*;
import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude="owners")   // avoid stack overflow
@Entity
@Table(name="car")
public class Car
{
    @Id()
    @Column(name = "car_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

    // On the target side, we only have to provide the name of the field,
    // which maps the relationship. https://www.baeldung.com/jpa-many-to-many
    @ManyToMany(mappedBy = "cars")
    private Set<Account> owners = new HashSet<>(0);
    //private Account owner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_model_id")
    private CarModel model;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "manufacture_year")
    private Integer manufactureYear;

    @Column(name = "mileage")
    private Integer mileage;
}
