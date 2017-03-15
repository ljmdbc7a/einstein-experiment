package com.einstein.experiment.reference;

import java.lang.ref.Reference;

/**
 *
 * @author liujiaming
 * @since 2017/03/15
 *
 */
public class RefCleanupThread extends Thread {

    private static boolean running = true;

    private static Thread threadRef;

    public RefCleanupThread() {
        super("RefCleanupThread");
    }

    @Override
    public void run() {
        threadRef = this;
        System.out.println("RefCleanupThread start.");
        while (running) {
            try {
                Reference<? extends RefObject> ref = PhantomRefTest.refQ.remove(100);
                if (ref != null) {
                    // can do something with ref
                    System.out.println("do something with ref and remove from map.");
                    Thread.sleep(10);
                    // remove from map
                    PhantomRefTest.refMap.remove(ref);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() throws InterruptedException {
        running = false;
        if (threadRef != null) {
            threadRef.interrupt();
            threadRef.join();
            threadRef = null;
        }
    }
}
