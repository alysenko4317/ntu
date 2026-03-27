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
                    System.out.println("запуск потоку-демона");
                    sleep(1000); // замінити параметр на 1
                }
                else {
                    System.out.println("запуск звичайного потоку");
                    sleep(100);
                }
            }
            catch (InterruptedException e) {
                System.err.print("Error" + e);
            }
            finally
            {
                if (! isDaemon()) {
                    System.out.println("завершення звичайного потоку");
                }
                else
                    System.out.println("завершення потоку-демона");
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

        System.out.println("останній оператор main");
    }
}
