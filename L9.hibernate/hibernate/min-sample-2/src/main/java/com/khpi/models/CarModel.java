package com.khpi.models;

import javax.persistence.*;

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

    //private Integer myfield;

    public CarModel() { }
    public CarModel(final Integer id) {
        this.pk = id;
    }

    public void setId(final Integer id) {
        this.pk = id;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setMake(String make) {
        this.make = make;
    }

    public Integer getId() {
        return this.pk;
    }
    public String getModel() {
        return this.model;
    }
    public String getMake() {
        return this.make;
    }

    public String getName() {
        return make + " " + model;
    }


}
