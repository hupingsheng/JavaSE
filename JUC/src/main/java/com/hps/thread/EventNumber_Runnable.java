package com.hps.thread;


/**
 * 1. 创建一个实现Runnable接口的类
 * 2. 实现接口中的run()，====> 将此线程要执行的操作，声明在此方法中
 * 3. 创建当前实现类的对象
 * 4. 将此对象作为参数传递到Thread类的构造器中，创建Thread类的实例
 * 5. Thread类的实例调用start()方法
 */
public class EventNumber_Runnable {

    public static void main(String[] args) {
        PrintNum printNum = new PrintNum();
        new Thread(printNum).start();
    }
}


class PrintNum implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0)
                System.out.println(Thread.currentThread().getName() + "  " + i);
        }
    }
}