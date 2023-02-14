package com.khpi.functional;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        MyInterface ref;

        ref = () -> 3.14;

        System.out.println("Value of Pi = " + ref.getPiValue());
    }
}
