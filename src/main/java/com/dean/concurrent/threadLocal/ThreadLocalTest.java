package com.dean.concurrent.threadLocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dean on 2018/5/29.
 */
public class ThreadLocalTest {



    public static ThreadLocal<SimpleDateFormat> threadLocal=new ThreadLocal<>();


    static class Task implements Runnable{
        private String name;
        private int i=0;
        public Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            while (i<10) {
                SimpleDateFormat simpleDateFormat = threadLocal.get();
                if (simpleDateFormat == null) {
                    System.out.println("init sft:" + name);
                    simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
                    threadLocal.set(simpleDateFormat);
                }
                System.out.println(name + ":" + simpleDateFormat.format(new Date()));
                i++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String []args){

        new Thread(new Task("zhang")).start();


    }


}
