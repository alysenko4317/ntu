package com.khpi.mvc.models;

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

  /*  @Override
    public String toString() {
        return "Car {" + registrationNumber + ", " + model + "}";
    }*/
}
