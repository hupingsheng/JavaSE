package com.hps.communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现三个线程交替打印 利用condition进行线程精准唤醒  多线程同步问题
 **/

public class PrintNum_Condition {
    public static void main(String[] args) {
        Data3 data3 = new Data3();

        new Thread(() -> {
            while (true){
                data3.printA();
            }
        }, "A").start();
        new Thread(() -> {
            while (true){
                data3.printB();
            }
        }, "B").start();
        new Thread(() -> {
            while (true){
                data3.printC();
            }
        }, "C").start();
    }

}

class Data3 {
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    private int flag = 1; // 1A 2B 3C

    int num = 1;

    public void printA() {
        lock.lock();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            // 业务代码 判断 -> 执行 -> 通知
            while (flag != 1) {
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "  " + num++);
            flag = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            // 业务代码 判断 -> 执行 -> 通知
            while (flag != 2) {
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + "  " + num++);
            flag = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            // 业务代码 判断 -> 执行 -> 通知
            while (flag != 3) {
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() + "  " + num++);
            flag = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
