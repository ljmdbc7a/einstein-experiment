package com.einstein.experiment.concurrency.safe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liujiaming
 * @since 2017/02/24
 */
public class ThreadPoolExecutorTest {

    private static final int THREADS = 500;
    /**
     * 使用Executors工具类创建线程池
     */
    private static final ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(THREADS);

    public static void main(String[] args) {

        Sequence sequence = new Sequence();
        for (int i = 0; i < 500; i++) {
            SequenceWorker worker = new SequenceWorker(sequence);
            threadPoolExecutor.execute(worker);
        }
    }

}
