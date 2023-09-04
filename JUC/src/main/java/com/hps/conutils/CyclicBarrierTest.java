package com.hps.conutils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier c = new CyclicBarrier(2);

        new Thread(()->{

            try {
                c.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            System.out.println(1);

        }).start();

        try {
            c.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println(2);
    }
}
