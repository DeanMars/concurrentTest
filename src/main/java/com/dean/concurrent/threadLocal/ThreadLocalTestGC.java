package com.dean.concurrent.threadLocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Dean on 2018/5/31.
 */
public class ThreadLocalTestGC {

    static volatile ThreadLocal<SimpleDateFormat> threadLocal=new ThreadLocal<>(){
        @Override
        protected void finalize() throws Throwable {
            System.out.println("threadLocal is gc ...");
            super.finalize();
        }
    };

    static volatile CountDownLatch countDownLatch=new CountDownLatch(1000);




    static class ParseDate implements Runnable{
        int i=0;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            SimpleDateFormat simpleDateFormat = threadLocal.get();
            if (simpleDateFormat == null) {
                System.out.println("init sft:" + i);
                simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
                threadLocal.set(simpleDateFormat);
            }
            System.out.println(i + ":" + simpleDateFormat.format(new Date()));
        }

    }



}
