package com.khpi.app;

public class Synchronized2
{
    public static synchronized void staticA()
    {
        System.out.println("entering staticA()");

        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException x) { }

        System.out.println("leaving staticA()");
    }

    public static void staticB()
    {
        synchronized (Synchronized2.class)
        {
            System.out.println("in staticB() : inside sync block");

            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException x) { }
        }
    }

    public static void main(String[] args)
    {
        Runnable runA = new Runnable() {
            public void run() {
                Synchronized2.staticA();
            }
        };

        Thread threadA = new Thread(runA, "A");
        threadA.start();

        try {
            Thread.sleep(200);
        }
        catch (InterruptedException x) { }

        Runnable runB = new Runnable() {
            public void run() {
                Synchronized2.staticB();
            }
        };

        Thread threadB = new Thread(runB, "B");
        threadB.start();
    }
}

