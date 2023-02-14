package com.khpi.interface_p;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        // объявление ссылки на параметризованны интерфейс, который принимает String
        // и присвоение ей (ссылке) лямбды
        GenericInterface<String> reverse = (str) -> {
            String r = "";
            for (int i = str.length() - 1; i >= 0; i--) {
                r += str.charAt(i);
            }
            return r;
        };

        System.out.println("Lambda reversed: " + reverse.func("Lambda"));

        // объявление ссылки на параметризованны интерфейс, который принимает Integer
        // и присвоение ей (ссылке) лямбды
        GenericInterface<Integer> factorial = (n) -> {
            int r = 1;
            for (int i = 1; i < n; i++) {
                r = i * r;
            }
            return r;
        };

        System.out.println("Factorial of 5 = " + factorial.func(5));
    }
}
