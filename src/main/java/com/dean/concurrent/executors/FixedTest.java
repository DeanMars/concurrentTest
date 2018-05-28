package com.dean.concurrent.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Dean on 2018/5/28.
 */
public class FixedTest implements Runnable {




    public static void main(String []args){
        ExecutorService executorService=Executors.newFixedThreadPool(5);
        //ExecutorService executorService=Executors.newCachedThreadPool();
        FixedTest fixedTest=new FixedTest();
        for(int i=0;i<10;i++){
            executorService.submit(fixedTest);
        }

        executorService.shutdown();
    }


    public void run() {
        System.out.println(System.currentTimeMillis()+":"+Thread.currentThread().getId());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
