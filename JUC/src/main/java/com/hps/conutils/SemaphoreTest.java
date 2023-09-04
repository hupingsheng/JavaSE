package com.hps.conutils;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore 信号量
 * 同一时刻，最多能释放的资源数  限流，控制最大并发线程数
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore s = new Semaphore(3);

        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                try {
                    s.acquire();   //申请资源
                    System.out.println(Thread.currentThread().getName() + "抢到车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "离开车位");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    s.release();  //释放资源
                }
            },String.valueOf(i)).start();
        }

    }

}
