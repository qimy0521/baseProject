package com.gcx.api;

import java.util.concurrent.*;

public class ExecutorServiceDemo {

    public static void main(String args[]){
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        BlockingQueue<Runnable> workQueue=new LinkedBlockingDeque<>();
        int sum=100;

        for (int a=0;a<sum;a++){
            final int count=0;
            Runnable run = () -> System.out.println(Thread.currentThread().getName()+">");
            workQueue.add(run);
        }

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(13, 20, 10000L, TimeUnit.SECONDS, workQueue);

    }
}
