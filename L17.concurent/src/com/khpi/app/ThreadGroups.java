package com.khpi.app;

public class ThreadGroups
{
    static class MyThread extends Thread {
        boolean suspended;

        MyThread(String threadName, ThreadGroup tgOb) {
            super(tgOb, threadName);
            System.out.println("New thread: " + this);
            suspended = false;
            start(); // Start the thread
        }

        public void run()
        {
            try {
                for (int i = 5; i > 0; i--) {
                    System.out.println(getName() + ": " + i);
                    Thread.sleep(1000);
                    synchronized (this) {
                        while (suspended) {
                            wait();
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Exception in " + getName());
            }
            System.out.println(getName() + " exiting.");
        }

        void suspendMe() {
            suspended = true;
        }
        synchronized void resumeMe() {
            suspended = false;
            notify();
        }
    }

    public static void main(String[] args)
    {
        ThreadGroup groupA = new ThreadGroup("Group A");
        ThreadGroup groupB = new ThreadGroup("Group B");

        MyThread ob1 = new MyThread("One", groupA);
        MyThread ob2 = new MyThread("Two", groupA);
        MyThread ob3 = new MyThread("Three", groupB);
        MyThread ob4 = new MyThread("Four", groupB);

        System.out.println("\nHere is output from list():");
        groupA.list();
        groupB.list();

        System.out.println("Suspending Group A");
        Thread tga[] = new Thread[groupA.activeCount()];
        groupA.enumerate(tga); // get threads in group

        for (int i = 0; i < tga.length; i++) {
            ((MyThread) tga[i]).suspendMe(); // suspend each thread
        }

        //

        System.out.println("Resuming Group A");
        tga = new Thread[groupA.activeCount()];
        groupA.enumerate(tga); // get threads in group

        for (int i = 0; i < tga.length; i++) {
            ((MyThread) tga[i]).resumeMe(); // suspend each thread
        }

        try
        {
            System.out.println("Waiting for threads to finish.");
            ob1.join();
            ob2.join();
            ob3.join();
            ob4.join();
        }
        catch (Exception e) {
            System.out.println("Exception in Main thread");
        }

        System.out.println("Main thread exiting.");
    }
}
