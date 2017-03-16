package com.einstein.experiment.reference;

import com.google.common.collect.Maps;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Field;
import java.util.Map;

/**
 *
 * @author liujiaming
 * @since 2017/03/14
 *
 * Phantom reference objects, which are enqueued after the collector
 * determines that their referents may otherwise be reclaimed.  Phantom
 * references are most often used for scheduling pre-mortem cleanup actions in
 * a more flexible way than is possible with the Java finalization mechanism.
 *
 * 虚引用:
 * 当gc发现虚引用时，会将PhantomReference对象放入referenceQueue中，而此时PhantomReference所指向的对象并没有被GC回收，而是等到referenceQueue移除相应元素的时才会被回收。
 * 虚引用一般用于对象被回收之前的清理工作。
 *
 * Phantom references are useful for implementing cleanup operations that are necessary before an object gets garbage-collected.
 * They are sometimes more flexible than the finalize() method.
 */
public class PhantomRefTest {

    public static final Map<RefObjectPhantomReference, RefObjectPhantomReference> refMap = Maps.newHashMap();
    public static final ReferenceQueue<RefObject> refQ = new ReferenceQueue<RefObject>();

    public static class RefObjectPhantomReference extends PhantomReference<RefObject> {
        public RefObjectPhantomReference(RefObject referent, ReferenceQueue<? super RefObject> q) {
            super(referent, q);
        }
    }

    public static void main(String[] args) {

        /**
         * 启动清理线程
         */
         new RefCleanupThread().start();

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            RefObject refObject = new RefObject();
            RefObjectPhantomReference refObjectPhantomReference = new RefObjectPhantomReference(refObject, refQ);
            refMap.put(refObjectPhantomReference, refObjectPhantomReference);

            System.out.println(refMap.size());
            // 反射获取队列长度
            Field privateField = null;
            try {
                privateField = ReferenceQueue.class.getDeclaredField("queueLength");
                privateField.setAccessible(true);
                Long fieldValue = (Long) privateField.get(refQ);
                System.out.println(refQ + " queueLength = " + fieldValue);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
