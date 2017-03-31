package com.einstein.experiment._effective_java;

/**
 *
 * @author liujiaming
 * @since 2017/03/27
 *
 * 总结：<code>volatile</code> 关键字实现可变变量在多线程间的可见性。
 * 如果加 <code>volatile</code> 关键字，A线程修改了共享变量的值，B线程可能（跟JVM的实现有关，mac jkd1.8 不复现）不会马上读到修改后的值。
 *
 */
public class VolatileTest {

    private static boolean stopFlag = false;
    //    private static volatile boolean stopFlag = false;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            int i = 0;

            @Override
            public void run() {
                while (!stopFlag) {
                    System.out.println(i++);
                }
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopFlag = true;
        System.out.println(stopFlag);
    }
}
