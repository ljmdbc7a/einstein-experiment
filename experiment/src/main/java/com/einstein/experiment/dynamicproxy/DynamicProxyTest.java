package com.einstein.experiment.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 * @author liujiaming
 * @since 2017/03/19
 *
 */
public class DynamicProxyTest {

    static class DynamicProxy implements InvocationHandler {

        Object originalObj;

        Object bind(Object originalObj) {
            this.originalObj = originalObj;
            return Proxy.newProxyInstance(originalObj.getClass()
                                                     .getClassLoader(),
                                          originalObj.getClass()
                                                     .getInterfaces(),
                                          this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // do something before
            System.out.println("do something before " + method.getName());
            // invoke method
            Object ret = method.invoke(originalObj, args);
            // do something after
            System.out.println("do something after " + method.getName());
            return ret;
        }
    }

    public static void main(String[] args) {
        MapInterface mapInterface = (MapInterface) new DynamicProxy().bind(new MapInterfaceImpl());
        System.out.println(mapInterface.calculateArea("bj"));
        System.out.println(mapInterface.isAccess("bj"));
    }

}
