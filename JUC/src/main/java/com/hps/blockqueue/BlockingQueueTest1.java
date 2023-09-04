package com.hps.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 阻塞队列
 * 抛出异常的方式  add remove
 */

public class BlockingQueueTest1 {
    public static void main(String[] args) {

        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(arrayBlockingQueue.add("a"));
        System.out.println(arrayBlockingQueue.add("b"));
        System.out.println(arrayBlockingQueue.add("b"));
       // IllegalStateException: Queue full
//        System.out.println(arrayBlockingQueue.add("d"));

        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
//        NoSuchElementException
//        System.out.println(arrayBlockingQueue.remove());
    }
}
