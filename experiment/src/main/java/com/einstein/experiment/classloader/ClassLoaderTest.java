package com.einstein.experiment.classloader;

import java.lang.reflect.Constructor;

/**
 * 自定义ClassLoader，反射获取实例。
 */
public class ClassLoaderTest {

    public static void printClassLoader(Class clazz) {
        ClassLoader loader = clazz.getClassLoader();
        while (loader != null) {
            System.out.println(loader.toString());
            loader = loader.getParent();
        }
    }

    public static void main(String[] args) {
        MyClassLoader myClassLoader = new MyClassLoader("/Users/liujiaming/Documents/code-open/leetcode-java/target/classes/");
        MyClassLoader myClassLoader2 = new MyClassLoader("/Users/liujiaming/Documents/code-open/leetcode-java/target/classes/");
        try {
            Class<?> clazz = myClassLoader.loadClass("com.ikouz.algorithm.leetcode.domain.ListNode");
            Constructor constructor = clazz.getDeclaredConstructor(new Class[] {int.class});
            Object clazzInstance = constructor.newInstance(new Object[] {1});
            Class<?> clazz2 = myClassLoader2.loadClass("com.ikouz.algorithm.leetcode.domain.ListNode");
            Object clazz2Instance = clazz2.newInstance();

            printClassLoader(clazz);
            printClassLoader(clazz2);
            //            Method method = clazz.getMethod("setInstance", Object.class);
            //            method.invoke(clazzInstance, clazz2Instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
