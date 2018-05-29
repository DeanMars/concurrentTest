package com.dean.concurrent.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by Dean on 2018/5/29.
 */
public class CountTask extends RecursiveTask<Long> {


    private static final int THRESHOLD=10000;

    private long start;

    private long end;

    public CountTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    protected Long compute() {
        long sum=0;
        boolean canC=(end-start)<THRESHOLD;
        if(canC){
            for(long i=start;i<end;i++){
                sum=+i;
            }
        }else{
            long step=(end-start)/100;
            List<CountTask> list=new ArrayList<>();
            long pos=start;
            for(int i=0;i<100;i++){
                long lastOne=pos+step;
                if(lastOne>end) lastOne=end;
                CountTask countTask=new CountTask(pos,lastOne);
                pos+=step+1;
                list.add(countTask);
                countTask.fork();
            }
            for(CountTask countTask:list){
                sum+=countTask.join();
            }

        }
        return sum;
    }



    public static void main(String []args){
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        CountTask countTask=new CountTask(0,20000);
        ForkJoinTask<Long> forkJoinTask=forkJoinPool.submit(countTask);
        try {
            long res=forkJoinTask.get();
            System.out.println("sum="+res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
