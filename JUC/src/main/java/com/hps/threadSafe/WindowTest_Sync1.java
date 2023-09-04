package com.hps.threadSafe;

/**
 * 同步方法
 *
 */
class SaleTicket_Sync1 implements Runnable {
    int ticket = 100;

    boolean isFlag = true;

    @Override
    public void run() {
        while (isFlag) {
            show();
        }
    }

    /**
     * 关于同步方法的同步监视器
     * 没有显示表示同步监视器，如何判断？
     * 如果同步方法是非静态的，那个同步监视器为this,===>saleTicket，唯一
     * 如果同步方法是静态的，那个同步监视器为当前类本身===>SaleTicket_Sync1.class
     */
    public synchronized void show() {
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
            isFlag = false;
        }
    }
}


public class WindowTest_Sync1 {
    public static void main(String[] args) {
        SaleTicket_Sync1 saleTicket = new SaleTicket_Sync1();

        Thread t1 = new Thread(saleTicket);
        Thread t2 = new Thread(saleTicket);
        Thread t3 = new Thread(saleTicket);

        t1.start();
        t2.start();
        t3.start();
    }
}
