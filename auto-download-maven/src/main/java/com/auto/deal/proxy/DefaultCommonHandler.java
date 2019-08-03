package com.auto.deal.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DefaultCommonHandler  <T> implements InvocationHandler{
    private T object;

    public DefaultCommonHandler(T o) {
        this.object = o;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        System.out.println(proxy instanceof );
//        System.out.println(proxy);
        System.out.println("before invoke");
        return method.invoke(object, args);
    }
}