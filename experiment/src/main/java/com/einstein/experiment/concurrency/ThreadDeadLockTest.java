package com.einstein.experiment.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程池中，如果任务依赖于其他任务，可能产生死锁。
 * <p/>
 * 例如：单线程Executor中，如果在一个任务中将另一个任务提交到同一个Executor，并且等待这个被提交任务的完成，则会发生死锁。
 *
 * @author liujiaming
 * @since 2017/02/25
 */
public class ThreadDeadLockTest {

    private static final ExecutorService exec = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        Future<String> renderPageFuture = exec.submit(new RenderPageTask());
        try {
            renderPageFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static class RenderPageTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            Future<String> headerFuture = exec.submit(new LoadFileTask());
            return headerFuture.get() + " Render page success.";
        }
    }

    public static class LoadFileTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            return "Load file success.";
        }
    }
}
