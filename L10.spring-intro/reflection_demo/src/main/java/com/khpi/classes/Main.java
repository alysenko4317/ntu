package com.khpi.classes;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        Scanner scanner = new Scanner(System.in);

        //-----------------------------------------------------
        // step6 - отримуємо інформацію про клас за його назвою
        //-----------------------------------------------------

        String className = "com.khpi.classes.Human";//scanner.next();    // "com.khpi.classes.Human"
        Class aClass = Class.forName(className);

        Field fields[] = aClass.getFields();
        for (Field field : fields) {
            System.out.println(field.getType() + " " + field.getName());
        }

        // step7 - створюємо екземпляр класу, маючи лише назву класу
        //         newInstance викликає конструктор без параметрів, якщо він є
        //         або викидає виняток
        //         or throws an exception
        //-----------------------------------------------------

        Object obj1 = aClass.newInstance();
        System.out.println(obj1);

        //-----------------------------------------------------
        // step8 - виклик конструктора з параметрами також можливий,
        //         але виконується дещо складніше
        //-----------------------------------------------------

        System.out.println("-----");

        Class types[] = new Class[fields.length];
        for (int i = 0; i < types.length; i++) {
            types[i] = fields[i].getType();
        }

        Constructor constructor = aClass.getDeclaredConstructor(types);
        for (Class parameterType : constructor.getParameterTypes()) {
            System.out.print("*" + parameterType.getName() + " ");
        }

        System.out.println("\n---");

        Integer intValue = 0;
        String stingValue = "";
        for (int i = 0; i < types.length; i++) {
            if (types[i].getName().equals("int"))
            {
                System.out.print("введіть параметр типу int: ");
                intValue = scanner.nextInt();
            } else if (types[i].getName().equals("java.lang.String"))
            {
                System.out.print("введіть параметр типу String: ");
                stingValue = scanner.next();
            }
        }

        Object arguments[] = { intValue, stingValue };
        Object object1 = constructor.newInstance(arguments);

        System.out.println(object1);
    }
}
