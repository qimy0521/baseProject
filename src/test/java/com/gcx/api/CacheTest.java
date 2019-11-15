package com.gcx.api;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 手写缓存工具类
 */
@Slf4j
public class CacheTest {

    private final static TimeUnit timeunit=TimeUnit.MILLISECONDS;

    /**
     * 储存的对象
     */
    private final static Map<String, Entity> map=new ConcurrentHashMap<>();

    /**
     * 可以设置延时执行的线程池
     */
    private final static ScheduledExecutorService executor=Executors.newSingleThreadScheduledExecutor();

    /**
     * 添加缓存,没有过期时间
     * @param key key值
     * @param data 数据对象
     */
    public static void put(String key,Object data){
        CacheTest.put(key,data,0L);
    }

    /**
     * 有过期时间的存放数据
     * @param key key值
     * @param data 数据对象
     * @param expire 过期时间
     */
    public synchronized static void put(String key,Object data,Long expire){
        if(expire > 0){
          Future future = executor.schedule(() -> {synchronized (CacheTest.class){
              log.info("移除key ==>{}  过期时间 ==>{}",key,expire);
              map.remove(key);} },expire,timeunit);
          map.put(key,new Entity(data,future));
        }else {
            map.put(key,new Entity(data,null));
        }
    }

    /**
     * 根据传入的class对象获取返回值对象
     * @param key 键
     * @return T
     */
    public static <T> T get(String key,Class<T> clazz){
        return clazz.cast(CacheTest.get(key));
    }

    private static Object get(String key) {
        Entity entity = map.get(key);
        return entity == null ? null : entity.getValue();
    }

    /**
     * 清除缓存
     */
    public synchronized static Object remove(String key) {
        //清除原缓存数据
        Entity entity = map.remove(key);
        if (entity == null) return null;
        //清除原键值对定时器
        Future future = entity.getFuture();
        if (future != null) future.cancel(true);
        return entity.getValue();
    }

    /**
     * 获取长度
     * @return 长度
     */
    public static long size(){
        return map.size();
    }
    /**
     * 储存的实体对象
     */
    @Getter
    private static class Entity{

        /**
         * 储存的值
         */
        private Object value;

        /**
         * 定时器Future 定时执行的任务
         */
        private Future future=null;

        /**
         * 构造方法
         * @param value 值的信息
         * @param future 定时器的预期执行对象
         */
        public Entity(Object value, Future future) {
            this.value = value;
            this.future = future;
        }

        public Entity(Object value) {
            this.value = value;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        String key = "id";
        //不设置过期时间
        System.out.println("***********不设置过期时间**********");
        CacheTest.put(key, 123);
        System.out.println("key:" + key + ", value:" + CacheTest.get(key));
        System.out.println("key:" + key + ", value:" + CacheTest.remove(key));
        System.out.println("key:" + key + ", value:" + CacheTest.get(key));

        //设置过期时间
        System.out.println("\n***********设置过期时间**********");
        CacheTest.put(key, "123456", 1000L);
        System.out.println("key:" + key + ", value:" + CacheTest.get(key));
        Thread.sleep(1000);
        System.out.println("key:" + key + ", value:" + CacheTest.get(key));
        /******************并发性能测试************/
        System.out.println("\n***********并发性能测试************");
        //创建有10个线程的线程池，将1000000次操作分10次添加到线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future[] futures = new Future[10];
        /********添加********/
        {
            long start = System.currentTimeMillis();
            for (int j = 0; j < 10; j++) {
                futures[j] = executorService.submit(() -> {
                    for (int i = 0; i < 100000; i++) {
                        CacheTest.put(Thread.currentThread().getId() + key + i, i, 300000L);
                    }
                });
            }
            //等待全部线程执行完成，打印执行时间
            for (Future future : futures) {
                future.get();
            }
            System.out.printf("添加耗时：%dms\n", System.currentTimeMillis() - start);
        }

        /********查询********/
        {
            long start = System.currentTimeMillis();
            for (int j = 0; j < 10; j++) {
                futures[j] = executorService.submit(() -> {
                    for (int i = 0; i < 100000; i++) {
                        CacheTest.get(Thread.currentThread().getId() + key + i);
                    }
                });
            }
            //等待全部线程执行完成，打印执行时间
            for (Future future : futures) {
                future.get();
            }
            System.out.printf("查询耗时：%dms\n", System.currentTimeMillis() - start);
        }
        System.out.println("当前缓存容量：" + CacheTest.size());
    }
}
