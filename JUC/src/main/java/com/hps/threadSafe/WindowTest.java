package com.hps.threadSafe;

class SaleTicket implements Runnable {
    int ticket = 100;
    @Override
    public void run() {
        while (true) {

                if (ticket > 0) {
                    //睡眠100ms,是为了多个线程在还未进程售票的时候，都能进入这个判断里面
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName() + "售票:票号：  " + ticket);
                    ticket--;
                }else {
                    break;
                }
            }
        }
    }


public class WindowTest {
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
