package com.hps.singletonPattern;

import java.util.concurrent.TimeUnit;

/**
 * 懒汉式单例模式
 *
 * 多线程下并发不安全
 */
public class LazyManDCLTest3 {

    private volatile static LazyManDCLTest3 LAZYMAN = null;

    private LazyManDCLTest3(){}

    //双重检测锁模式  懒汉式单例   DCL懒汉式
    public static LazyManDCLTest3 getInstance(){
        if(LAZYMAN == null){
            synchronized (LazyManDCLTest3.class){
                if (LAZYMAN == null){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    LAZYMAN = new LazyManDCLTest3();       // new 不是一个原子性操作
                    /**
                     * 1、分配内存空间
                     * 2、执行构造方法，初始化对象
                     * 3、先把这个对象指向这个空间
                     *
                     * 正常情况下  1 2 3的步骤
                     * 指令优化下 有可能出现  1 3 2的步骤
                     * 如果指令优化下执行到3的时候，此时另一个线程过来了，则判断if (LAZYMAN == null)直接跳过了，返回一个null的
                     * 解决方案 ： 加Volatile 禁止指令重排
                     */
                }
            }



        }
        return LAZYMAN;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                LazyManDCLTest3 instance = getInstance();
                System.out.println(instance);
            }).start();

        }
    }
}
