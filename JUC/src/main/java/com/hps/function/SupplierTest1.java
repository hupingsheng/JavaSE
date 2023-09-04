package com.hps.function;

import java.util.function.Supplier;

/**
 * 供给型接口：只有返回值，没有参数
 */
public class SupplierTest1 {
    public static void main(String[] args) {
//        Supplier supplier = new Supplier<Integer>(){
//            @Override
//            public Integer get() {
//                System.out.println("get...");
//                return 1024;
//            }
//        };

        Supplier supplier = () -> {
            System.out.println("get...");
            return 1024;
        };
        System.out.println(supplier.get());
    }
}
