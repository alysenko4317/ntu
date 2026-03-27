package com.khpi.dog;

public class Dog implements Comparable<Dog> {
    String color;
    Dog(String c) {
        color = c;
    }

    @Override
    public int compareTo(Dog o) {
        return this.color.compareTo(o.color);
    }
}

/*public class Dog{
    String color;
    Dog(String c) {
        color = c;
    }
}*/