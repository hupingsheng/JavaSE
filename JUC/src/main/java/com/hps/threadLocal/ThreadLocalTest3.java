package com.hps.threadLocal;

import java.util.concurrent.TimeUnit;

/**
 *  线程并发下 数据安全问题
 *  在多线程并发的场景下，每个线程中的变量都是相互独立的
 *  线程A:   设置（变量1）  获取变量1
 *  线程B:   设置（变量2）  获取变量2
 *  synchronized 解决
 *
 */
public class ThreadLocalTest3 {

    private String content;

    private String getContent() {
        return content;
    }

    private void setContent(String content) {
        this.content = content;
    }

    public static void main(String[] args) {
        ThreadLocalTest3 demo = new ThreadLocalTest3();

        for (int i = 1; i <= 5 ; i++) {
            final int temp = i;
            new Thread(()->{

                synchronized (ThreadLocalTest3.class){
                    demo.setContent(Thread.currentThread().getName() + "的数据");

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println(Thread.currentThread().getName() + "获取" + demo.getContent());
                }

            },temp+"").start();
        }
    }
}
