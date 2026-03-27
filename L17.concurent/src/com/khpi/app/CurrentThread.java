package com.khpi.app;

public class CurrentThread
{
    public static void main(String args[])
    {
        Thread t = Thread.currentThread();
        System.out.println("Поточний потік: " + t);

        // змінити ім'я потоку
        t.setName("My Thread");
        System.out.println("Після зміни імені: " + t);

        try
        {
            for (int n = 5; n > 0; n--) {
                System.out.println(n);
                Thread.sleep(1000);
            }
        }
        catch (InterruptedException e) {
            System.out.println("Головний потік завершено");
        }
    }
}
