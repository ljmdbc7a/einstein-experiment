package com.einstein.experiment.bestpractice.futureTask;

import java.util.concurrent.Callable;

/**
 *
 */
public class MockTask implements Callable<String> {

    private String key;

    public MockTask(String key) {
        this.key = key;
    }

    public String call() throws Exception {
        // start remote call
        System.out.println("get value from service for " + key);
        // simulate the delay
        Thread.sleep(1000);
        return "v-" + key;
    }
}
