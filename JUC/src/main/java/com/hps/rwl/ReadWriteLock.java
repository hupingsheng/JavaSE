package com.hps.rwl;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁，写入的时候不允许别的线程插队，读的时候不管
 *
 * 1写入1
 * 1写入OK
 * 3写入3
 * 3写入OK
 * 2写入2
 * 2写入OK
 * 5写入5
 * 5写入OK
 * 4写入4
 * 4写入OK
 * 1读取1
 * 1读取OK
 * 3读取3
 * 4读取4       线程4插队了
 * 4读取OK
 * 2读取2
 * 3读取OK
 * 5读取5
 * 5读取OK
 * 2读取OK
 */
public class ReadWriteLock {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        //写入
        for (int i = 1; i <= 5 ; i++) {
            final int temp = i;
            new Thread(()->{
                myCache.put(temp + "", temp + "");
            }, String.valueOf(i)).start();
        }

        //读取
        for (int i = 1; i <= 5 ; i++) {
            final int temp = i;
            new Thread(()->{
                myCache.get(temp + "");
            }, String.valueOf(i)).start();
        }
    }
}


class MyCache{
    private volatile Map<String, String> map = new HashMap<>();

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    Collection list = new ArrayList();
    //读写锁
    //存，写   只有一个线程可以写入
    public void put(String key, String value){
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写入" + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入OK");
        }finally {
            lock.writeLock().unlock();
        }
    }

    //取 读     所有人都可以读
    public void get(String key){
        lock.readLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + "读取" + key);
            map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取OK");
        }finally {
            lock.readLock().unlock();
        }
    }
}