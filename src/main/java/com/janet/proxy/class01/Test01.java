package com.janet.proxy.class01;

import java.lang.reflect.Proxy;

/**
 * Date: 2022/6/8
 * Time: 22:56
 *
 * @author jimas
 */
public class Test01 {
    public static void main(String[] args) {
        //System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles","true");
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        RunAble able = new Dog();
        RunAble runAble = (RunAble) Proxy.newProxyInstance(Cat.class.getClassLoader(), new Class[]{RunAble.class}, new TimeInvocationHandler(able));
        runAble.run();
    }
}
