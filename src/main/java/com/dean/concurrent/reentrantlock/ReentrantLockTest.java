package com.dean.concurrent.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest implements Runnable{

    public static ReentrantLock lock=new ReentrantLock();

    private static int count=0;

    public static void main(String []args)throws InterruptedException{
        ReentrantLockTest test=new ReentrantLockTest();
        Thread t1= new Thread(test);
        Thread t2= new Thread(test);
        t1.start();t2.start();
        t1.join();t2.join();
        System.out.println(count);
    }

    public void run() {
        for(int i=0;i<10000000;i++ ){
            lock.lock();
            try {
                count++;
            }finally {
                lock.unlock();
            }
        }
    }
}
