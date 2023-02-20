package com.khpi.db.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account
{
    private Integer pk;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;
    private List<Car> cars;
}
