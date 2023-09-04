package com.hps.singletonPattern;

import java.util.concurrent.TimeUnit;

/**
 * 懒汉式单例模式
 *
 * 多线程下并发不安全
 */
public class LazyManDCLTest2 {

    private static LazyManDCLTest2 LAZYMAN = null;

    private LazyManDCLTest2(){}

    //双重检测锁模式  懒汉式单例   DCL懒汉式
    public static  LazyManDCLTest2 getInstance(){
        if(LAZYMAN == null){
            synchronized (LazyManDCLTest2.class){
                if (LAZYMAN == null){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    LAZYMAN = new LazyManDCLTest2();
                }
            }



        }
        return LAZYMAN;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                LazyManDCLTest2 instance = getInstance();
                System.out.println(instance);
            }).start();

        }
    }
}
