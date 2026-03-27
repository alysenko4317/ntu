package com.khpi.app;

public class Main
{
    public static class Talk extends Thread
    {
        public void run()
        {
            for (int i = 0; i < 8; i++)
            {
                System.out.println("Talking");
                try
                {
                    // зупинка на 400 мілісекунд
                    Thread.sleep(400);
                }
                catch (InterruptedException e) {
                    System.err.print(e);
                }
            }
        }
    }

    public static class Walk implements Runnable
    {
        public void run()
        {
            for (int i = 0; i < 8; i++)
            {
                System.out.println("Walking");
                try
                {
                    Thread.sleep(400);
                }
                catch (InterruptedException e) {
                    System.err.println(e);
                }
            }
        }
    }

    public static void main(String[] args)
    {
        // нові об'єкти потоків
        Talk talk  = new Talk();
        Thread walk = new Thread(new Walk());

        // запуск потоків
        talk.start();
        walk.start();

        // Walk w = new Walk(); // просто об'єкт, не потік
        // w.run(); // метод виконається, але потік не запуститься!
    }
}
