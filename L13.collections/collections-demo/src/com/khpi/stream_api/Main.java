package com.khpi.stream_api;

import java.util.*;

public class Main {

    // объявление списка
    static List<String> places = new ArrayList<>();

    // заполнение данными
    public static List getPlaces() {
        // добавление страны и города
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

        // фильтрация городов
        myPlaces.stream()
                .filter((p) -> p.startsWith("Nepal"))
                .map((p) -> p.toUpperCase())
                .sorted()
                .forEach((p) -> System.out.println(p));
    }
}
