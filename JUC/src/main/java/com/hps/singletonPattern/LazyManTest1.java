package com.hps.singletonPattern;

import java.util.concurrent.TimeUnit;

/**
 * 懒汉式单例模式
 *
 * 多线程下并发不安全
 */
public class LazyManTest1 {

    private static LazyManTest1 LAZYMAN = null;

    private LazyManTest1(){}

    public static synchronized LazyManTest1 getInstance(){
        if(LAZYMAN == null){

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            LAZYMAN = new LazyManTest1();
        }
        return LAZYMAN;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                LazyManTest1 instance = getInstance();
                System.out.println(instance);
            }).start();

        }
    }
}
