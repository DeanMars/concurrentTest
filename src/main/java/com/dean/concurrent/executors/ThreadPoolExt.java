package com.dean.concurrent.executors;

import java.util.concurrent.*;

/**
 * Created by Dean on 2018/5/29.
 */
public class ThreadPoolExt {

    public static class Task  implements Runnable{
        public String name;

        public Task(String name) {
            this.name = name;
        }

        public void run() {
            System.out.println(System.currentTimeMillis()+":"+Thread.currentThread().getId()+":"+name);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }




    public static void main(String []args) throws InterruptedException{
        ExecutorService executorService=new ThreadPoolExecutor(5,5,0,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>()){
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("beforeExecute "+((Task)r).name);
            }

            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("afterExecute "+((Task)r).name);
            }

            protected void terminated() {
                System.out.println("terminated");
            }

        };
        for(int i=0;i<5;i++){
            executorService.execute(new Task("ThreadPoolExt"+i));
            Thread.sleep(100);
        }

        executorService.shutdown();


    }

}
