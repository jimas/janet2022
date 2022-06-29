package com.janet.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Date: 2022/6/8
 * Time: 23:50
 *
 * @author jimas
 */
public class Test01 {
    public static void main(String[] args) {
        final Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Hourse.class);
        enhancer.setCallback(new RunMethodInterceptor());
        final Hourse o = (Hourse) enhancer.create();
        o.run();
    }
}

class RunMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println(o.getClass().getSuperclass().getName());
        System.out.println("before");
        final Object invoke = methodProxy.invokeSuper(o, objects);
        System.out.println("after");
        return invoke;
    }
}

class Hourse {
    public void run() {
        System.out.println("^ run ^");
    }
}
