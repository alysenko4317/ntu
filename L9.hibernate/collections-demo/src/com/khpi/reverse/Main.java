package com.khpi.reverse;

import java.util.*;

public class Main {

    public static Iterator<String> reverse(List<String> list) {
        Collections.reverse(list);
        return list.iterator();
    }

    public static void main(String[] args)
    {
        List<String> list = Arrays.asList("1", "2", "3");
        //for (String str : reverse(list))
        //    System.out.print(str + ", ");
    }
}
