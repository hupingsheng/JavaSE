package com.hps.collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 并发修改异常
 * Exception in thread "2" Exception in thread "0" java.util.ConcurrentModificationException
 */
public class ListTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

//        List<String> list = new Vector<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());

//        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 30; i++) {

            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(list.size());
    }
}
