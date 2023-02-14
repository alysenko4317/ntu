package com.khpi.functional_2;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        MyInterface ref;

        ref = (str) -> {
            String r = "";
            for (int i = str.length() - 1; i >= 0; i--) {
                r += str.charAt(i);
            }
            return r;
        };

        System.out.println("Lambda reversed: " + ref.reverse("Lambda"));
    }
}
