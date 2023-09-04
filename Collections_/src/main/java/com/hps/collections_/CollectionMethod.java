package com.hps.collections_;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

public class CollectionMethod {
    public static void main(String[] args) {

        ArrayList list = new ArrayList(8);

        list.add("jack");
        list.add(10);
        list.add(true);

        System.out.println(list);
        System.out.println(list.size());

        Object[] arr = new Object[10];
        arr[0] = 1;
        arr[1] = 2;

        for (int i = 0; i < 10; i++) {
            System.out.println(arr[i]);
        }
        System.out.println(arr.length);


        Vector vector = new Vector<>();

        new Hashtable<>();

    }
}
