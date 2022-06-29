package com.janet.class01;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * 模拟下 linkedList
 * Date: 2022/6/3
 * Time: 15:27
 *
 * @author jimas
 */
public class Code_06_LinkedList {
    public static void main(String[] args) {
        MyLinkedList<String> linkedList = new MyLinkedList<String>();
        linkedList.add("sss");
        linkedList.add("ssass");
        linkedList.add("ssssd");
        System.out.println(linkedList);
        System.out.println(linkedList.size);
        System.out.println(linkedList.get(0));
        System.out.println(linkedList.get(1));
        System.out.println(linkedList.get(2));

        List<String> objects = new ArrayList<String>();
        objects.add("ss");

        final ListIterator<String> iterator = objects.listIterator();
    }

    public static class MyLinkedList<E> {
        private Node first;
        private Node last;
        private int size;

        public boolean add(E e) {
            //1、创建node节点对象
            Node newNode = new Node<E>(last, e, null);
            if (first == null) {
                //2、引用指向
                first = newNode;
                last = newNode;
            } else {
                last.next = newNode;
                last = newNode;
            }
            size++;
            return true;
        }

        public E get(int index) {
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node.obj;
        }

        public int getSize() {
            return size;
        }


    }

    public static class Node<E> {
        private Node pre;
        private E obj;
        private Node next;

        public Node() {
        }

        public Node(Node pre, E obj, Node next) {
            this.pre = pre;
            this.obj = obj;
            this.next = next;
        }

        public Node getPre() {
            return pre;
        }

        public void setPre(Node pre) {
            this.pre = pre;
        }

        public E getObj() {
            return obj;
        }

        public void setObj(E obj) {
            this.obj = obj;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

    }

}
