package com.hps.threadLocal;

import java.util.concurrent.TimeUnit;

/**
 * 线程并发下 数据安全问题
 * 在多线程并发的场景下，每个线程中的变量都是相互独立的
 * 线程A:   设置（变量1）  获取变量1
 * 线程B:   设置（变量2）  获取变量2
 * <p>
 * ThreadLocal:
 * 1. set():   将变量绑定到当前线程中
 * 2. get():   获取当前线程绑定的变量
 */
public class ThreadLocalTest2 {

    private String content;

    ThreadLocal<String> t = new ThreadLocal<>();

    private String getContent() {
        return t.get();
    }

    private void setContent(String content) {
        //将content绑定到当前线程
        t.set(content);
    }

    public static void main(String[] args) {
        ThreadLocalTest2 demo = new ThreadLocalTest2();

        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                demo.setContent(Thread.currentThread().getName() + "的数据");

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println(Thread.currentThread().getName() + "获取" + demo.getContent());
            }, temp + "").start();
        }
    }
}
