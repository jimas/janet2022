package com.janet.class03;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

/**
 * 双端队列
 * Date: 2022/6/13
 * Time: 23:25
 *
 * @author jimas
 */
public class MyDequeTest {
    private static class MyNode<V> {
        private V v;
        private MyNode<V> pre;
        private MyNode<V> next;

        public MyNode(V v) {
            this.v = v;
        }


    }

    private static class MyDeque<V> {
        private MyNode<V> head;
        private MyNode<V> tail;
        private int size;

        public void addFirst(V v) {
            final MyNode<V> newNode = new MyNode<>(v);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                //新节点下个节点指向head
                newNode.next = head;
                //head 的前驱指向 新节点
                head.pre = newNode;
                //头指针前移动
                head = newNode;
            }
            size++;
        }

        public void addLast(V v) {
            final MyNode<V> newNode = new MyNode<>(v);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                newNode.pre = tail;
                tail = newNode;
            }
            size++;
        }

        public V getFirst() {
            if (head == null) {
                return null;
            }
            V ans = head.v;
            return ans;
        }

        public boolean empty() {
            return size == 0;
        }
    }

    public static void main(String[] args) {

        int times = 10000;
        final Deque<String> deque = new LinkedList<>();
        final MyDeque<String> myDeque = new MyDeque<>();
        for (int i = 0; i < times; i++) {
            final double num = Math.random();
            if (num < 0.35) {
                deque.addFirst(num + "");
                myDeque.addFirst(num + "");
            } else if (0.36 < num && num < 0.68) {
                deque.addLast(num + "");
                myDeque.addLast(num + "");
            } else {
                if (deque.isEmpty() && myDeque.empty()) {

                } else {
                    final String first = deque.getFirst();
                    final String first1 = myDeque.getFirst();
                    if (!Objects.equals(first, first1)) {
                        System.out.println("fail");
                    }
                }

            }
        }
    }
}
