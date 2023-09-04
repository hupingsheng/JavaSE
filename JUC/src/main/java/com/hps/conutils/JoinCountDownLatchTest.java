package com.hps.conutils;

/**
 * 等待别的线程完成，完成操作，再执行自己的
 * join:让当前执行线程等待join线程执行结束
 */

public class JoinCountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            System.out.println("线程1 parse1 finished!");
        });

        Thread t2 = new Thread(()->{
            System.out.println("线程2 parse2 finished!");
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("主线程 finished!");
    }

}
