package com.hps.function;

import java.util.function.Function;

/**
 * Function 函数型接口 , 有一个输入参数，有一个输出
 * 只要是 函数式接口， 就可以用 lambda 表达式简化
 */
public class FunctionTest1 {

    public static void main(String[] args) {

        //工具类：输出输入的值
//        Function function = new Function<String, String>() {
//            @Override
//            public String apply(String s) {
//                return s;
//            }
//        };

        // lambda表达式 简化
        Function function = (str)->{
            return str;
        };
        System.out.println(function.apply(1));

    }
}
