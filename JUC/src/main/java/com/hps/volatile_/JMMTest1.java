package com.hps.volatile_;

import java.util.concurrent.TimeUnit;

/**
 * 正常情况下，线程之间读取的一个公共变量修改后是不可见的
 *  * 比如这里，main线程明明已经修改了num=1，并且刷回主内存了，但是在运行中的分线程不知道，仍然认为num = 0,死循环中
 *  ====>  volatile可以解决这个问题
 */
public class JMMTest1 {
    static int num = 0;
    public static void main(String[] args) {

        new Thread(() ->{
            while (num == 0){};
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        num = 1;

        System.out.println(num);
    }
}
