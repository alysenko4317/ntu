package com.khpi.db.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude="owner")   // avoid stack overflow
public class Car
{
    private Integer pk;
    private Account owner;
    private CarModel model;
    private String registrationNumber;
    private Integer manufactureYear;
    private Integer mileage;
}
