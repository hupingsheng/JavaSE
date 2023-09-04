package com.hps.threadPool;



/**
 * 使用原始的线程方法  创建线程  new Thread()
 *
 *
 */
public class ExecutorsTest0 {
    public static void main(String[] args) {

            for (int i = 1; i <= 10; i++) {
                //使用线程池创建线程
                new Thread(()->{
                    System.out.println(Thread.currentThread().getName() + "  OK");
                }).start();
            }


    }
}
