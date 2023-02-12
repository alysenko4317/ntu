package com.khpi.demo;

import java.lang.reflect.Field;
import java.util.Scanner;

public class Main
{
    public static void main(String [] argv) throws Exception
    {
        System.out.println("hello!");

        // step 1 - определим класс и создадим экхемпляр этого класса
        MyClass o = new MyClass();

        // step 2 - рассмотрим такие встроенные классы как Class и Field, которые позволяют
        // получать информацию о типе конкретного объекта (экземпляра какого-либо класса)
        Class<MyClass> myClass = (Class<MyClass>) o.getClass();

        Field foo = myClass.getField("foo");
        System.out.println(foo.toString() + " " + foo.getType());

        // step3 - посмотрим какие поля имеются у обьекта o
        Field[] fields = myClass.getFields();
        for (Field f : fields)
            System.out.println(f.getType() + " "+ f.getName());

        // step4: используем рефлексию чтобы изменять значение поля в обьекте
        System.out.println(o.foo);
        foo.set(o, 777);
        System.out.println(o.foo);
        myClass.getField("foo").set(o, 888);
        System.out.println(o.foo);

        // step5: какова ситуация с закрытыми полями ?
        for (Field f : myClass.getDeclaredFields())
            System.out.println(f.getType() + " "+ f.getName());

        Field privateField = myClass.getDeclaredField("_hidden");
        System.out.println(privateField.getType());

        privateField.setAccessible(true);
        privateField.set(o, "It works!");
        System.out.println(o.getHidden());
    }
}
