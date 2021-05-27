package dev.liambloom.softwareEngineering.chapterL30.printChar2;

public class Client
{
    public static void main(String[] args)
    {
        final int N = 100;

        // Create tasks
        Runnable printA = new PrintChar2("a1",N);
        Runnable printB = new PrintChar2("b1",N);
        Runnable printC = new PrintChar2("c1",N);

        // Create threads
        Thread thread1 = new Thread(printA);
        Thread thread2 = new Thread(printB);
        Thread thread3 = new Thread(printC);

        // Start threads
        thread1.start();
        thread2.start();
        thread3.start();

    }// main

} //  TaskThreadDemo_LiangChap39_pg1100_PrintChar2_CLIENT

