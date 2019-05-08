package com.einstein.experiment.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadpoolStrategy {


    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 100, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10));

        for (int i = 0; i < 10000; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + " --- " + i);
                Thread.sleep(10);
            } catch (Exception e) {
            }
            threadPoolExecutor.submit(() -> {
                try {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(100);
                } catch (Exception e) {
                }
            });
        }
    }
}
