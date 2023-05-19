package com.khpi.for_;

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
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) == 1) {
                numbers.remove(i);
            }
        }
        for (Integer number : numbers) {
            System.out.println(number);
        }
    }
}
