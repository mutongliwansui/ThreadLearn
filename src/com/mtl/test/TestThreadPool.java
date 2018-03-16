package com.mtl.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 通过线程池执行任务的案例
 */
public class TestThreadPool {
    public static void main(String[] args) {
//        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
       ThreadPoolExecutor executor = new ThreadPoolExecutor(5,10,200, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(5));
        for (int i=0;i<15;i++){
            MyTask mytask = new MyTask(i);
            executor.execute(mytask);
            System.out.println("线程池中线程的数目:"+executor.getPoolSize()+",队列中等待执行任务的任务数目:"+executor.getQueue().size()+",已执行的任务数目:"+executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }
}
class MyTask implements Runnable{
    private int tasknum;

    public MyTask(int tasknum) {
        this.tasknum = tasknum;
    }
    @Override
    public void run() {
        System.out.println("正在执行任务"+tasknum);
        try {
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行任务"+tasknum+"完毕！");
    }
}
