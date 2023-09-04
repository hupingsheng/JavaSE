package com.hps.cas;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 原子引用解决ABA问题
 */
public class ABATest2 {
    static AtomicStampedReference<Integer> atomicStampedReference =
            new AtomicStampedReference<>(1,1);

    //
    public static void main(String[] args) {
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();  //获得的版本号
            System.out.println("初始的版本号a1  " + stamp);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //修改操作版本号 + 1
            atomicStampedReference.compareAndSet(1,2,
                    atomicStampedReference.getStamp(),
                    atomicStampedReference.getStamp() + 1
                    );
            System.out.println("版本号 a2  " + atomicStampedReference.getStamp());

            System.out.println(atomicStampedReference.compareAndSet(2, 1,
                    atomicStampedReference.getStamp(),
                    atomicStampedReference.getStamp() + 1
            ));

            System.out.println("版本号 a3  " + atomicStampedReference.getStamp());
        }).start();


        new Thread(() ->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println("初始版本号b1  " + stamp);

            /**
             * 等待上面线程执行完
             */
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(atomicStampedReference.compareAndSet(1, 3,
                    stamp, stamp + 1
            ));

            System.out.println("b2  " + atomicStampedReference.getStamp());
        }).start();


    }
}
