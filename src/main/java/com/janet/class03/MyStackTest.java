package com.janet.class03;

import java.util.Objects;
import java.util.Stack;

/**
 * 单链表 实现 栈 先进后出
 * Date: 2022/6/13
 * Time: 23:26
 *
 * @author jimas
 */
public class MyStackTest {
    private static class MyNode<V> {
        private V v;
        private MyNode<V> next;

        public MyNode(V v) {
            this.v = v;
        }
    }

    private static class MyStack<V> {
        private MyNode<V> head;
        private int size;

        public int getSize() {
            return size;
        }

        public void push(V v) {
            final MyNode<V> newNode = new MyNode<>(v);
            if (head == null) {
                head = newNode;
            } else {
                newNode.next = head;
                head = newNode;
            }
            size++;
        }

        public V pop() {
            if (head == null) {
                return null;
            }
            V ans = head.v;
            head = head.next;
            size--;
            return ans;
        }

        public boolean empty() {
            return size == 0;
        }
    }

    public static void main(String[] args) {
        int times = 1000000;
        final Stack<String> stack = new Stack<>();
        final MyStack<String> myStack = new MyStack<>();
        for (int i = 0; i < times; i++) {
            final double num = Math.random();
            if (num < 0.35) {
                stack.push(num + "");
                myStack.push(num + "");
            } else {
                if (stack.empty() && myStack.empty()) {

                } else {
                    final String stackVal = stack.pop();
                    final String myVal = myStack.pop();
                    if (!Objects.equals(stackVal, myVal)) {
                        System.out.println("fail");
                    }
                }
            }
        }
    }
}
