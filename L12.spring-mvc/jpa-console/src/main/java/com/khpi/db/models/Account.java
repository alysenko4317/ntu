package com.khpi.db.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Account
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "car_account_link",
        joinColumns = @JoinColumn(name = "account_id"),
        inverseJoinColumns = @JoinColumn(name = "car_id"))
    private List<Car> cars;
}
