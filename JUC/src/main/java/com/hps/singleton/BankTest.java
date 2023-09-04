package com.hps.singleton;

/**
 * 懒汉式
 */
public class BankTest {
    static Bank b1 = null;
    static Bank b2 = null;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                b1 = Bank.getInstance();
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                b2 = Bank.getInstance();
            }
        };

        t1.start();
        t2.start();

        //保证t1,t2分线程执行完
        t1.join();
        t2.join();


        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b1 == b2);
    }
}


class Bank{
    //单例模式，构造函数是私有的，不允许直接对单例类直接new出来
    private Bank(){};

    private static Bank instance = null;

    //同步监视器为当前类本身 Bank.class
    public static synchronized Bank getInstance(){
        if (instance == null){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            instance = new Bank();
        }

        return instance;
    }
}
