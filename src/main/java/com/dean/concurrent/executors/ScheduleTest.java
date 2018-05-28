package com.dean.concurrent.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dean on 2018/5/28.
 */
public class ScheduleTest implements Runnable {
    public void run() {
        System.out.println(Thread.currentThread().getId()+":"+System.currentTimeMillis());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public static void main(String []args){
       ScheduledExecutorService executorService=Executors.newScheduledThreadPool(10);
       ScheduleTest scheduleTest=new ScheduleTest();
        System.out.println("main"+Thread.currentThread().getId()+":"+System.currentTimeMillis());
       for(int i=0;i<10;i++){
           //从上一个任务开始时间开始延后10秒
           //executorService.scheduleAtFixedRate(scheduleTest,5,10,TimeUnit.SECONDS);
           //从上一个任务结束时间开始延后10秒
           executorService.scheduleWithFixedDelay(scheduleTest,5,10,TimeUnit.SECONDS);
       }
      // executorService.shutdown();

    }
}
