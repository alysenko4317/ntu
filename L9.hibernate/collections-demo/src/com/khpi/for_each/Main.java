package com.khpi.for_each;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(1);
        numbers.add(1);
        for (Integer number : numbers) {
            if (number == 1) {
                numbers.remove(number);
            }
        }
        for (Integer number : numbers) {
            System.out.println(number);
        }
    }
}
