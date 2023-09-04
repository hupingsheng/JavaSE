package com.hps.communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock实现交替打印
 */
public class PrintNum_LockTest {

    public static void main(String[] args) {
        PrintNum_Lock printNum_lock = new PrintNum_Lock();
        new Thread(printNum_lock).start();

        new Thread(printNum_lock).start();
    }
}

class PrintNum_Lock implements Runnable {

    int num = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    @Override
    public void run() {
        while (true) {
            lock.lock();

            try {
                condition.signal();
                if (num < 100) {
                    num++;
                    System.out.println(Thread.currentThread().getName() + "打印了  " + num);


                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }


                } else {
                    break;
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
