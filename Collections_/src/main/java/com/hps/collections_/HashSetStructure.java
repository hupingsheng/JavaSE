package com.hps.collections_;

import java.util.HashMap;

/**
 * 模拟数组链表
 */
public class HashSetStructure {
    public static void main(String[] args) {


        HashMap<Object, Object> map = new HashMap<>();
        map.put("name", "zs");


        Node[] table = new Node[16];
        System.out.println("table=  " + table);


        Node join = new Node("join", null);

        table[2] = join;
        Node jack = new Node("jack", null);
        join.next = jack;  //将jack节点 挂载到 join
        System.out.println("table=  " + table);



    }
}

//节点
class Node{
    Object item;
    Node next;

    public Node(Object item, Node next) {
        this.item = item;
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "item=" + item +
                ", next=" + next +
                '}';
    }
}
