package com.hps.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 阻塞队列
 * 返回特殊值的方式  offer poll
 */

public class BlockingQueueTest2 {
    public static void main(String[] args) {

        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(arrayBlockingQueue.offer("a"));
        System.out.println(arrayBlockingQueue.offer("b"));
        System.out.println(arrayBlockingQueue.offer("b"));
       // 容器已满返回 false
//        System.out.println(arrayBlockingQueue.offer("d"));

        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
//        容器已空 返回 null
        System.out.println(arrayBlockingQueue.poll());
    }
}
