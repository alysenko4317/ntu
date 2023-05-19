package com.khpi.mvc.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="car_model")
public class CarModel
{
    @Id()
    @Column(name = "car_model_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

    private String model;
    private String make;
}
