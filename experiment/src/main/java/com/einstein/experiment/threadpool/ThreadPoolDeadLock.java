package com.einstein.experiment.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Submit some tasks to a thread pool. Sub-task uses the same thread pool. Parent task need to wait sub-task to finish.
 * If the threads of the pool is not enough to use, it causes dead lock: sub-tasks wait the available thread which is hold by parent tasks.
 */
public class ThreadPoolDeadLock {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            int taskNum = i;
            executorService.submit(() -> {
                System.out.println("Parent task started. num: " + taskNum + Thread.currentThread().getName());
                try {
                    Thread.sleep(100); // wait few millis to ensure all parent tasks start before sub-tasks
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Future subTaskFuture = executeSubTask(executorService, taskNum);
                try {
                    subTaskFuture.get(); // block to waiting available thread
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                System.out.println("Parent task ended. num: " + taskNum + Thread.currentThread().getName());
            });
        }
    }

    private static Future<Integer> executeSubTask(ExecutorService executorService, int input) {
        return executorService.submit(() -> {
            System.out.println("SubSubSubSubSubSubSub task. num: " + input + Thread.currentThread().getName());
            return input;
        });
    }
}
