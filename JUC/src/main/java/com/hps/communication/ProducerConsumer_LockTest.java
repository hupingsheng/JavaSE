package com.hps.communication;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * lock解决虚假唤醒   condition:await signALl
 */
public class ProducerConsumer_LockTest {

    public static void main(String[] args) {

        Clerk_lock clerk_lock = new Clerk_lock();

        Producer_lock producer = new Producer_lock(clerk_lock);
        Consumer_lock consumer = new Consumer_lock(clerk_lock);

        new Thread(producer).start();
        new Thread(consumer).start();

        new Thread(consumer).start();
        new Thread(consumer).start();
        new Thread(consumer).start();
        new Thread(consumer).start();
        new Thread(consumer).start();
        new Thread(consumer).start();

    }
}


class Clerk_lock{
    int num = 0;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void add(){
        lock.lock();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            if(num >= 20){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            num ++;
            System.out.println(Thread.currentThread().getName() + "生产了第 " + num + " 个产品");
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }


    public void sub(){
        lock.lock();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            if(num <= 0){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(Thread.currentThread().getName() + "消费了第 " + num + " 个产品");
            num --;
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}

class Producer_lock implements Runnable{
    private Clerk_lock clerk_lock;

    public Producer_lock(Clerk_lock clerk_lock) {
        this.clerk_lock = clerk_lock;
    }


    @Override
    public void run() {
        while (true){
            clerk_lock.add();
        }
    }
}

class Consumer_lock implements Runnable{
    private Clerk_lock clerk_lock;

    public Consumer_lock(Clerk_lock clerk_lock){
        this.clerk_lock = clerk_lock;
    }
    @Override
    public void run() {
        while (true){
            clerk_lock.sub();
        }
    }
}