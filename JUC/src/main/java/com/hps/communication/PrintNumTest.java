package com.hps.communication;


/**
 * 线程间通信的理解：
 * 当我们需要多个线程来共同完成一件任务，并且我们希望他们有规律的执行，那么线程之间需要一些通信机制，
 * 可以协调他们的工作，以此实现多线程共同操作一份数据
 *
 * wait,notify notifyAll
 * 注意点：这三个方法的使用，必须在同步代码块或同步方法中
 *      （Lock需要配合Condition实现线程间的通信）
 *       这三个方法的调用者必须是同步方法或同步代码块的监视器。
 * 任务：实现两个线程交替打印1到100
 * 1.多线程先考虑线程安全，用同步代码块
 * 2.交替打印, 两个线程轮流拿锁
 */
public class PrintNumTest {

    public static void main(String[] args) {

        PrintNum printNum = new PrintNum();
        Thread t1 = new Thread(printNum, "线程1");
        Thread t2 = new Thread(printNum, "线程2");

        t1.start();
        t2.start();

    }

}

class PrintNum implements Runnable {

    private int num = 1;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                //本线程已经顺利拿到锁了，别的线程就别阻塞了，
                notify();

                if (num <= 100) {


                    System.out.println(Thread.currentThread().getName() + "打印：" + num);
                    num++;

                    try {
                        //现将自己阻塞起来并释放锁，方便别的线程顺利抢到锁，并等他抢到锁后，唤醒自己
                        wait();   //线程一旦执行此方法，就进入等待状态。会释放对同步监视器的调用（即释放锁）
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                } else {
                    break;
                }


            }
        }
    }
}
