package com.khpi.demo;

import java.lang.reflect.Field;
import java.util.Scanner;

public class Main
{
    public static void main(String [] argv) throws Exception
    {
        System.out.println("hello!");

        // step 1 - визначимо клас та створимо екземпляр цього класу
        MyClass o = new MyClass();

        // step 2 - розглянемо такі вбудовані класи як Class та Field, які дозволяють
        // отримувати інформацію про тип конкретного об'єкта (екземпляра будь-якого класу)
        Class<MyClass> myClass = (Class<MyClass>) o.getClass();

        Field foo = myClass.getField("foo");
        // step3 - подивимось, які поля є у об'єкта o

        // step3 - see what fields the object "o" has
        Field[] fields = myClass.getFields();
        for (Field f : fields)
        // step4: використовуємо рефлексію, щоб змінювати значення поля в об'єкті

        // step4: use reflection to change the value of a field in the object
        System.out.println(o.foo);
        foo.set(o, 777);
        System.out.println(o.foo);
        myClass.getField("foo").set(o, 888);
        // step5: яка ситуація із закритими полями?

        // step5: what about private fields?
        for (Field f : myClass.getDeclaredFields())
            System.out.println(f.getType() + " " + f.getName());

        Field privateField = myClass.getDeclaredField("hidden");
        System.out.println(privateField.getType());

        privateField.setAccessible(true);
        privateField.set(o, "It works!");
        System.out.println(o.getHidden());
    }
}
