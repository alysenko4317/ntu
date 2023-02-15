package com.khpi.order;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<String> set = new TreeSet<>();
        set.add("one");
        set.add("two");
        set.add("three");
        set.add("four");
        set.add("one");
        for (String str : set) {
            System.out.print(str + " ");
        }
    }
}
