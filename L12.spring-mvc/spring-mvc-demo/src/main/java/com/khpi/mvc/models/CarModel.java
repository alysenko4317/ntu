package com.khpi.mvc.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarModel
{
    private Integer pk;
    private String model;
    private String make;

  /*  @Override
    public String toString() {
        return model + ' ' + make;
    }*/
}
