package com.hps.conutils;

import java.util.concurrent.CyclicBarrier;

/**
 *
 */
public class CyclicBarrierTest2 {
    public static void main(String[] args) {
        //构造函数，传进去一个runnable接口
        CyclicBarrier c = new CyclicBarrier(7,()->{
            System.out.println("召唤神龙成功。。。");
        });

        for (int i = 1; i <= 7; i++) {
            final int temp = i;
            new Thread(() -> {

                System.out.println(Thread.currentThread().getName() + "收集 " + temp + " 个龙珠");

                try {
                    c.await();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }



            }).start();
        }

    }
}
