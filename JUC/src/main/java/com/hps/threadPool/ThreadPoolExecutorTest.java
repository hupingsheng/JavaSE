package com.hps.threadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程 7大参数
 *  拒绝策略
 *  1. new ThreadPoolExecutor.AbortPolicy()   RejectedExecutionException 抛异常
 *  2. new ThreadPoolExecutor.CallerRunsPolicy()   谁把任务分发来的，回哪去 main线程
 *  3. new ThreadPoolExecutor.DiscardPolicy()  任务不执行，直接丢弃掉
 *  4. new ThreadPoolExecutor.DiscardOldestPolicy() 丢弃掉任务队列中，最老的任务
 */
public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        //自定义线程池　　
        // 最大线程数，究竟该定义多大
        // CPU密集型
        // IO密集型
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );

        try {
            // 最大承载:   Queue + max 并发线程数超过，则执行拒绝策略
            for (int i = 1; i <= 9; i++) {
                final int temp = i;
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " 输出 " + temp);
                });
            }
        } finally {
            //关闭
            threadPool.shutdown();
        }

    }
}
