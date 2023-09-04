package com.hps.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 与之前的方式的对比:与Runnable方式的对比的好处
 * >calL()可以有返回值，更灵活
 * >calL()可以使用throws的方式处理异常，更灵活
 * >Callable使用了泛型参数，可以指明具体的call()的返回值类型，更灵活
 * 有缺点吗?
 * 如果在主线程中需要获取分线程call()的返回值，则此时的主线程是阻塞状态的
 *
 */
class NumThread implements Callable {

    int sum = 0;
    
    @Override
    public Object call() throws Exception {
        
        for(int i = 1; i <= 100; i++){
            if(i % 2 == 0){
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}

public class CallableTest {

    public static void main(String[] args) {
        NumThread numThread = new NumThread();

        FutureTask futureTask = new FutureTask(numThread);

        Thread t1 = new Thread(futureTask);
        t1.start();

        try {
            Object sum = futureTask.get();
            System.out.println("总和为：" + sum);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
