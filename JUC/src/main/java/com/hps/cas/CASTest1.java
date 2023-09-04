package com.hps.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CASTest1 {

    // CAS 比较并交换
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2020);

        //期望、更新
        // public final boolean compareAndSet(int expect, int update)
        // 如果我期望的值达到了，就更新，否则，就不更新  CAS是CPU的原语
        System.out.println(atomicInteger.compareAndSet(2020,2021));
        System.out.println(atomicInteger.get());

        atomicInteger.getAndIncrement();
        System.out.println(atomicInteger.compareAndSet(2020, 2021));
        System.out.println(atomicInteger.get());
    }
}
