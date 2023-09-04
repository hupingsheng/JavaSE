package com.hps.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 演示ABA现象
 */
public class ABATest1 {
    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(2020);

        new Thread(()->{
            //2020 -> 2021 -> 2020   演示ABA现象
            System.out.println(atomicInteger.compareAndSet(2020, 2021));
            System.out.println(atomicInteger.get());

            System.out.println(atomicInteger.compareAndSet(2021, 2020));
            System.out.println(atomicInteger.get());
        }).start();



        new Thread(()->{
            //此线程业务繁忙，用睡眠表示
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


            System.out.println(atomicInteger.compareAndSet(2020, 2021));
            System.out.println(atomicInteger.get());
        }).start();

    }
}
