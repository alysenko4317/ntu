package com.khpi.inner_class;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        ClassA objA = new ClassA();
        Interface objB = objA.getB(5);
        ObjectOutputStream out = new ObjectOutputStream(
            new FileOutputStream("test.bin"));
        out.writeObject(objB);
        out.close();
    }
}
