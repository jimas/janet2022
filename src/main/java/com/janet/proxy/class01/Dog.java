package com.janet.proxy.class01;

import java.util.Random;

/**
 * Date: 2022/6/8
 * Time: 22:54
 *
 * @author jimas
 */
public class Dog implements RunAble {
    @Override
    public String run() {
        System.out.println("dog run fast……");
        try {
            Thread.sleep((int) (Math.random() * 10) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "DOG";
    }
}
