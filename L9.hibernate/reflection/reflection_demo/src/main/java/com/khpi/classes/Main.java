package com.khpi.classes;

//import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        Scanner scanner = new Scanner(System.in);

        //-----------------------------------------------------
        // step6 - получаем информацию о классе по его названию
        //-----------------------------------------------------

        String className = "com.khpi.classes.Human";//scanner.next();    // "com.khpi.classes.Human"
        Class aClass = Class.forName(className);

        Field fields[] = aClass.getFields();
        for (Field field : fields) {
            System.out.println(field.getType() + " " + field.getName());
        }

        //-----------------------------------------------------
        // step7 - создаём экземпляр класса имея лишь название класса
        //         newInstance вызываеь конструктор без параметров, если он есть
        //         либо выбрасывает исключение
        //-----------------------------------------------------

        Object obj1 = aClass.newInstance();
        System.out.println(obj1);

        //-----------------------------------------------------
        // step8 - вызов конструктора с параметрами также возможен,
        //         но выполняется несколько сложнее
        //-----------------------------------------------------

        Class types[] = new Class[fields.length];
        for (int i = 0; i < types.length; i++) {
            types[i] = fields[i].getType();
        }

        Constructor constructor = aClass.getDeclaredConstructor(types);
        for (Class parameterType : constructor.getParameterTypes()) {
            System.out.print(parameterType.getName() + " ");
        }

        Integer intValue = 0;
        String stingValue = "";
        for (int i = 0; i < types.length; i++) {
            if (types[i].getName().equals("int"))
            {
                System.out.print("введите параметр типа int: ");
                intValue = scanner.nextInt();
            } else if (types[i].getName().equals("java.lang.String"))
            {
                System.out.print("введите параметр типа String: ");
                stingValue = scanner.next();
            }
        }

        Object arguments[] = { intValue, stingValue };
        Object object1 = constructor.newInstance(arguments);

        System.out.println(object1);
    }
}
