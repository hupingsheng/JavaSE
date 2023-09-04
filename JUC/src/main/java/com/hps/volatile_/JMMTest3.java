package com.hps.volatile_;


import java.util.concurrent.atomic.AtomicInteger;

/**
 *  验证volatile
 *  num++ 不是原子操作，volatile不能保证原子性
 *  那么如何保证原子性?
 *  lock synchronized
 *  原子类 AtomicInteger
 *  原子类的底层类都是和操作系统挂钩的，在内存中直接修改值， unsafe类是一个很特殊的存在
 */
public class JMMTest3 {
//    private static volatile int num = 0;

    private static AtomicInteger num =  new AtomicInteger();
    public  static void add(){
        num.getAndIncrement();
    }

    public static void main(String[] args) {


        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }

        // main gc 跳过这个循环，说明只剩下main 和 gc线程了，上面的线程都执行完了
        while (Thread.activeCount() > 2){
            Thread.yield();
        }

        System.out.println(num);
    }
}
