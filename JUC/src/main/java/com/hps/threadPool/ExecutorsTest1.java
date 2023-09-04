package com.hps.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用线程池方法创建线程
 * Executors方法
 *
 * 底层用的都是  new ThreadPoolExecutor()方法
 */
public class ExecutorsTest1 {
    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();  //单个线程
         ExecutorService threadPool = Executors.newFixedThreadPool(5);   //创建一个固定大小的线程
//        ExecutorService threadPool = Executors.newCachedThreadPool();   // 可伸缩的，遇强则强，遇弱则弱


        try {
            for (int i = 0; i < 100; i++) {
                //使用线程池创建线程
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }
        }finally {
            //线程池用完关闭线程
            threadPool.shutdown();
        }

    }
}
