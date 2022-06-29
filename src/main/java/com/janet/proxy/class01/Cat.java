package com.janet.proxy.class01;

/**
 * Date: 2022/6/8
 * Time: 22:55
 *
 * @author jimas
 */
public class Cat implements RunAble{
    @Override
    public String run() {
        System.out.println("cat run slow……");
        try {
            Thread.sleep((int) (Math.random() * 10) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "CAT";
    }
}
