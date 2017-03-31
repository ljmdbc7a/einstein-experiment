package com.einstein.experiment.concurrency;

/**
 *
 * @author liujiaming
 * @since 2017/03/31
 *
 */
public class ThreadDeadLockOfSynchronizedTest {

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void update12() {
        synchronized (lock1) {
            System.out.println("update12 locked lock1, do something");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2) {
                System.out.println("update12 locked lock2, do something");
            }
        }
    }

    public void update21() {
        synchronized (lock2) {
            System.out.println("update21 locked lock2, do something");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock1) {
                System.out.println("update21 locked lock1, do something");
            }
        }
    }

    public static void main(String[] args) {
        final ThreadDeadLockOfSynchronizedTest test = new ThreadDeadLockOfSynchronizedTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test.update12();
            }
        }).start();
        //        try {
        //            Thread.sleep(2000);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                test.update21();
            }
        }).start();
    }
}
