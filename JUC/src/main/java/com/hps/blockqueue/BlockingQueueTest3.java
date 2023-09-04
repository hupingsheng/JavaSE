package com.hps.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 阻塞队列
 * 返回特殊值的方式  put  take
 */

public class BlockingQueueTest3 {
    public static void main(String[] args) throws InterruptedException {

        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);

        arrayBlockingQueue.put("a");
        arrayBlockingQueue.put("b");
        arrayBlockingQueue.put("c");

       // 容器已满再插入 直接阻塞
//        arrayBlockingQueue.put("d");

        arrayBlockingQueue.take();
        arrayBlockingQueue.take();
        arrayBlockingQueue.take();

        //容器已空再删除，直接阻塞
//        arrayBlockingQueue.take();

//        容器已空 返回 null

    }
}
