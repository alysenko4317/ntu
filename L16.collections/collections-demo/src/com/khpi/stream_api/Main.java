package com.khpi.stream_api;

import java.util.*;

public class Main {

    // оголошення списку
    static List<String> places = new ArrayList<>();

    // заповнення даними
    public static List getPlaces() {
        // додавання країни та міста
        places.add("Nepal, Kathmand");
        places.add("Nepal, Pokhara");
        places.add("India, Delhi");
        places.add("USA, New York");
        places.add("Africa, Nigeria");
        return places;
    }

    public static void main(String[] args)
    {
        List<String> myPlaces = getPlaces();
        System.out.println("Places from Nepal:");

        // фільтрація міст
        myPlaces.stream()
                .filter((p) -> p.startsWith("Nepal"))
                .map((p) -> p.toUpperCase())
                .sorted()
                .forEach((p) -> System.out.println(p));
    }
}
