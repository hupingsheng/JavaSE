package com.hps.volatile_;

import java.util.concurrent.TimeUnit;

/**
 * 验证volatile的可见性
 */
public class JMMTest2 {
    //加了volatile 保证可见性
    static volatile int num = 0;
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
