package com.khpi.inner_class;

import java.io.Serializable;

class ClassA implements Serializable
{
    private class ClassB implements Interface
    {
        private int a;
        public int getA() { return a; }
    }

    private ClassB[] arr = new ClassB[10];

    {
        for (int i = 0; i < 10; i++) {
            arr[i] = new ClassB();
            arr[i].a = i + 65;
        }
    }

    public Interface getB(int index) {
        return arr[index];
    }
}
