package com.einstein.experiment.bestpractice.futureTask;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 单机时，为了避免并发带来的任务多次重复操作，把futureTask放入cache，而不是futureTask.get()
 */
public class PutFutureTaskInCacheTest {

    private static Map<String, FutureTask> futureTaskMapCache = new ConcurrentHashMap<>();

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static String serviceCall(String key) {
        FutureTask<String> futureTaskNew = new FutureTask<>(new MockTask(key));
        FutureTask futureTask = futureTaskMapCache.putIfAbsent(key, futureTaskNew);
        if (futureTask == null) { // no problem?
            futureTask = futureTaskNew;
            executorService.submit(futureTask);
        }
        try {
            String value = (String) futureTask.get();
            System.out.println("got value: " + value);
            return value;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
//        new Thread(() -> serviceCall("key")).start();
        new Thread(() -> serviceCall("key")).start();

    }

}
