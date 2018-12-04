package com.gcx.api.CompletableFuture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Auther: root
 * @Date: 2018/9/28 11:32
 * @Description:
 */
public class CompletableFutureTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List myList=new ArrayList<>();
        List list = Collections.synchronizedList(myList);


        CompletableFuture<Void> future =
                CompletableFuture.runAsync(() -> {
                    try {
                        Thread.sleep(0);

                        System.out.println("hello");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

        future.get();
        System.out.println("world");
    }
}
