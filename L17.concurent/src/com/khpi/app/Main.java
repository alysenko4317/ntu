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
                    // остановка на 400 миллисекунд
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
        // новые объекты потоков
        Talk talk  = new Talk();
        Thread walk = new Thread(new Walk());

        // запуск потоков
        talk.start();
        walk.start();

        // Walk w = new Walk(); // просто объект, не поток
        // w.run(); //выполнится метод, но поток не запустится!
    }
}
