package com.einstein.experiment.reference;

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
public class PhantomRefTest {}
