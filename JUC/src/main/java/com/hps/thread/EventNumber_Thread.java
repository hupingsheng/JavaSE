package com.hps.thread;

/**
 * 创建线程打印 1-100之间的偶数
 * 1.创建一个继承于Thread的子类
 * 2.重写Thread类的run()方法--->此线程执行的任务，写在这个方法中
 * 3.创建当前Thread的子类对象
 * 4.通过对象调用start()方法  作用 1,启动线程，2.调用run方法
 */

public class EventNumber_Thread {

    public static void main(String[] args) {
        PrintNumber t1 = new PrintNumber();
        t1.start();


//        t1.start();   已经执行过的start,不能再用了，源码是判断if (threadStatus != 0) 抛异常

//        再创建一个分线程，执行打印任务，不能再用t1了，必须新建一个Thread子类对象
        PrintNumber t2 = new PrintNumber();
        t2.start();


        //主线程打印任务
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0)
                System.out.println(Thread.currentThread().getName() + "  " + i);
        }




    }
}


class PrintNumber extends Thread {

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0)
                System.out.println(Thread.currentThread().getName() + "  "+ i);
        }
    }
}
