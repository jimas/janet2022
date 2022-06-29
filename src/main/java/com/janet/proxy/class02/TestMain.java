package com.janet.proxy.class02;

/**
 * briage 桥接模式
 * 分离 抽象与实现，避免类爆炸。用聚合代理继承
 * Date: 2022/6/11
 * Time: 16:20
 *
 * @author jimas
 */
public class TestMain {
    public static void main(String[] args) {
        WarmGift warmGift = new WarmGift(new Flower());
        ColdGift coldGift = new ColdGift(new Book());
    }
}
