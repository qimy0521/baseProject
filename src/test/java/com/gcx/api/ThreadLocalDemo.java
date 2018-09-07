package com.gcx.api;

public class ThreadLocalDemo {

    private ThreadLocal<Long> longLocal=new ThreadLocal<>();
    private ThreadLocal<String> stringLocal = new ThreadLocal<>();

    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public long getLong() {
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalDemo threadLocalDemo=new ThreadLocalDemo();
        threadLocalDemo.set();
        System.out.println(threadLocalDemo.getLong());
        System.out.println(threadLocalDemo.getString());

        Thread thread= new Thread(() -> {
            threadLocalDemo.set();
            System.out.println(threadLocalDemo.getLong());
            System.out.println(threadLocalDemo.getString());
        });
        thread.start();
        thread.join();
    }

}
