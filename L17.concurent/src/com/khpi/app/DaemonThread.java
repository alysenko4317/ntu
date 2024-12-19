package com.khpi.app;

public class DaemonThread
{
    static class T extends Thread
    {
        public void run()
        {
            try
            {
                if (isDaemon()) {
                    System.out.println("старт потока-демона");
                    sleep(1000); // заменить параметр на 1
                }
                else {
                    System.out.println("старт обычного потока");
                    sleep(100);
                }
            }
            catch (InterruptedException e) {
                System.err.print("Error" + e);
            }
            finally
            {
                if (! isDaemon()) {
                    System.out.println("завершение обычного потока");
                }
                else
                    System.out.println("завершение потока-демона");
            }
        }
    }

    public static void main(String[] args)
    {
        T usual = new T();
        T daemon = new T();
        daemon.setDaemon(true);
        daemon.start();
        usual.start();

        System.out.println("последний оператор main");
    }
}
