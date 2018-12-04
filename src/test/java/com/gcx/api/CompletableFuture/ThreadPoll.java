package com.gcx.api.CompletableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Auther: root
 * @Date: 2018/9/28 15:11
 * @Description:
 */
public class ThreadPoll implements Runnable {
    private final LinkedBlockingQueue<Runnable> queue;

    private final List<Thread> threads;

    private boolean shutdown;

    public ThreadPoll(int threadSize) {
        queue = new LinkedBlockingQueue<>();
        threads = new ArrayList<>();
        for (int i=0; i<threadSize; i++) {
            Thread thread = new Thread(this);
            thread.start();
            threads.add(thread);
        }
    }




    @Override
    public void run() {

    }
}
