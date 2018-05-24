package com.dean.concurrent.deadLock;

/**
 * Created by Dean on 2018/5/15.
 *
 * 尽量避免死锁
 * 避免一个线程同时获取多个锁
 * 避免一个线程在锁内占用多个资源，尽量保证一个锁只占用一个资源
 * 尝试使用定时所 lock.tryLock（timeout）来替代内部所机制
 * 数据库锁，加锁和解锁在一个数据库连接里面。
 *
 *
 */
public class DeadLockDemo {

    private static String A="A";
    private static String B="B";



    public static void main(String []args){
        deadLock();
    }

    public static void deadLock(){
        Thread t1=new Thread(new Runnable() {
            public void run() {
                synchronized (A){
                    try {
                        Thread.currentThread().sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B){
                        System.out.println("1");
                    }
                }
            }
        });


        Thread t2=new Thread(new Runnable() {
            public void run() {
                synchronized (B){
                    try {
                        Thread.currentThread().sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (A){
                        System.out.println("2");
                    }
                }
            }
        });


        t1.start();
        t2.start();
    }







}
