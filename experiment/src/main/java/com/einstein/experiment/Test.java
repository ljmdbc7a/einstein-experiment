package com.einstein.experiment;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) {

        System.out.println("Starting....");
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        System.out.println(executorService);
        executorService.submit(() -> {
            try {
                Thread.sleep(10000);
                System.out.println("sleep end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread = new Thread();
        thread.setDaemon(true);
        thread.start();

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main end...");
    }
}
