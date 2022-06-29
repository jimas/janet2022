package com.janet.class03;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * 单链表 实现队列 先进先出
 * Date: 2022/6/13
 * Time: 23:03
 *
 * @author jimas
 */
public class MyQueueTest {

    private static class MyNode<V> {

        private V value;
        private MyNode<V> next;

        public MyNode(V value, MyNode<V> next) {
            this.value = value;
            this.next = next;
        }
    }

    public static class MyQueue<V> {
        private MyNode<V> head;
        private MyNode<V> tail;
        private int size;

        public boolean isEmpty() {
            return size == 0;
        }

        public int getSize() {
            return size;
        }

        public void put(V v) {
            MyNode<V> newNode = new MyNode<>(v, null);
            //说明newNode是第一个
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
            size++;
        }

        public V poll() {
            if (head == null) {
                return null;
            }
            V ans = head.value;
            if (head == tail) {
                //最后一个节点时，tail 置为null 释放节点（JVM 不可达算法，会垃圾回收）
                tail = null;
            }
            head = head.next;
            size--;
            return ans;
        }
    }

    public static void main(String[] args) {
        int times = 1000000;
        MyQueue<String> myQueue = new MyQueue<>();
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < times; i++) {
            double num = Math.random();
            if (num < 0.44) {
                myQueue.put(num + "");
                queue.add(num + "");
            } else {
                String headMyVal = myQueue.poll();
                String headVal = queue.poll();
                if (!Objects.equals(headMyVal, headVal)) {
                    System.out.println("fail");
                }
            }

        }

    }

}
