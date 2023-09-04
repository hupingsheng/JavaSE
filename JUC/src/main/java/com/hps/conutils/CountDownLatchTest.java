package com.hps.conutils;

import java.util.concurrent.CountDownLatch;

/**
 * countdownLatch方法可以用在任何地方，可以是N个点，也可以是N个线程
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        //总数是2，
        CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(()->{
//            必须等执行任务的时候再使用
            System.out.println("线程1 parse1 finished!");
            countDownLatch.countDown(); // 数量 -1操作
        }).start();



        new Thread(()->{
            System.out.println("线程2 parse2 finished!");
            countDownLatch.countDown(); // 数量 -1操作
        }).start();

        //计数器归零，再向下执行
        countDownLatch.await();

        System.out.println("主线程 finished!");
    }
}
