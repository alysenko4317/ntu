package com.khpi.classes;

public class Human
{
    public int age;
    public String name;

    public Human() {
        age = 10;
        name = "constructed w/o parameters";
    }

    public Human(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.age + " " + this.name;
    }
}
