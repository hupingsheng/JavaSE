package com.hps.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 用Lock解决线程安全问题
 */
class SaleTicket implements Runnable {
    int ticket = 100;

    //1、创建一个lock实例，确保多个线程共用一个lock实例
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            // 2、执行lock方法，锁定对共享资源的调用
            lock.lock();
            try {
                if (ticket > 0) {
                    //睡眠100ms,是为了多个线程在还未进程售票的时候，都能进入这个判断里面
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName() + "售票:票号：  " + ticket);
                    ticket--;
                } else {
                    break;
                }
            } finally {
                //3、unlock()的调用：释放对共享资源的锁定
                //锁状态
                System.out.println("锁状态" + lock.toString());
                lock.unlock();

            }

        }
    }
}


public class LockTest {
    public static void main(String[] args) {
        SaleTicket saleTicket = new SaleTicket();

        Thread t1 = new Thread(saleTicket);
        Thread t2 = new Thread(saleTicket);
        Thread t3 = new Thread(saleTicket);

        t1.start();
        t2.start();
        t3.start();
    }
}
