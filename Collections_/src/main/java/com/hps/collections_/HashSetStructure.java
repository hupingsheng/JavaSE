package com.hps.collections_;

import java.util.HashMap;

/**
 * ģ����������
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
        join.next = jack;  //��jack�ڵ� ���ص� join
        System.out.println("table=  " + table);



    }
}

//�ڵ�
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
