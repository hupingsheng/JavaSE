package com.hps.singletonPattern;

public class Hungry {

    //设置成私有的
    private Hungry() {
    }

    //static 类启动的时候就加载出来
    private final static Hungry HUNGRY = new Hungry();

    public static Hungry getInstance(){
        return HUNGRY;
    }
}
