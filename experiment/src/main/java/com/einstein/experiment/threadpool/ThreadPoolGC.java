package com.einstein.experiment.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPool instance will not be GC unless you call shutdown after submit all tasks.
 */
public class ThreadPoolGC {

    public static class MyObjectOverrideFinalize {

        private String flag;

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("MyObjectOverrideFinalize finalize called. " + flag);
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }
    }

    public static class ThreadPoolExecutorOverrideFinalize extends ThreadPoolExecutor {

        private String threadPoolName;

        public ThreadPoolExecutorOverrideFinalize(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override
        protected void finalize() {
            super.finalize();
            System.out.println("ThreadPoolExecutorOverrideFinalize finalize called. " + threadPoolName);
        }

        public void setThreadPoolName(String threadPoolName) {
            this.threadPoolName = threadPoolName;
        }
    }

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService.submit(() -> {
                ThreadPoolExecutorOverrideFinalize threadPoolExecutor = new ThreadPoolExecutorOverrideFinalize(1, 100, 60, TimeUnit.SECONDS,
                        new LinkedBlockingQueue<>(10));
                threadPoolExecutor.setThreadPoolName("Iam a threadPool which created by " + Thread.currentThread().getName());
                threadPoolExecutor.submit(() -> {
                    for (int j = 0; j < 10; j++) {
                        MyObjectOverrideFinalize myObjectOverrideFinalize = new MyObjectOverrideFinalize();
                        myObjectOverrideFinalize.setFlag("Created by " + Thread.currentThread().getName() + " count: " + j);
                    }
//                    System.out.println(Thread.currentThread().getName() + " newed 10 MyObjectOverrideFinalize instance...");
                });
//                threadPoolExecutor.shutdown();
            });
        }
    }

}
