package com.khpi.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(1);
        numbers.add(1);
        Iterator iterator = numbers.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(Integer.valueOf(1))) {
                iterator.remove();
            }
        }
        for (Integer number : numbers) {
            System.out.println(number);
        }
    }
}
