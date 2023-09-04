package com.hps.threadSafe;

/**
 * synchronized(同步监视器){
 *     //需要被同步的代码块
 * }
 * 说明：
 *  1、需要被同步的代码块，就是操作共享数据的代码（判断和执行）
 *  2、共享数据：即多个线程都会操作的数据，比如ticket
 *  3、需要被同步的代码，在被synchronized包裹以后，使得一个线程在操作这些代码的过程中，其他线程必须等待
 *  4、同步监视器，俗称锁。那个线程获取了锁，就拥有了执行同步代码块的权利
 *  5、同步监视器，可以使用任何一个类的对象来充当。但是，多个线程必须共用同一个同步监视器（对于多个线程来说，这个锁是唯一的，所以同一时刻只能有一个线程获取锁，也就是同一时刻只有一个线程可以操作公用代码块）
 *
 */
class SaleTicket_Sync implements Runnable {
    int ticket = 100;

    Object obj = new Object();  //充当锁，必须唯一的
    @Override
    public void run() {
        while (true) {
            //调用这个方法的对象，谁调用这个方法了===>SaleTicket_Sync的类对象，我们只new了一个，所以是唯一的
            synchronized (this) {
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
            }
        }
    }
}


public class WindowTest_Sync {
    public static void main(String[] args) {
        SaleTicket_Sync saleTicket = new SaleTicket_Sync();

        Thread t1 = new Thread(saleTicket);
        Thread t2 = new Thread(saleTicket);
        Thread t3 = new Thread(saleTicket);

        t1.start();
        t2.start();
        t3.start();
    }
}
