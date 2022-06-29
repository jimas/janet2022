package com.janet.proxy.class01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Date: 2022/6/8
 * Time: 22:58
 *
 * @author jimas
 */
public class TimeInvocationHandler implements InvocationHandler {
    private RunAble runAble;

    public TimeInvocationHandler(RunAble runAble) {
        this.runAble = runAble;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始run");
        long start = System.currentTimeMillis();
        final Object o = method.invoke(runAble, args);
        System.out.println(o + " 耗时：" + (System.currentTimeMillis() - start));
        return o;
    }
}
