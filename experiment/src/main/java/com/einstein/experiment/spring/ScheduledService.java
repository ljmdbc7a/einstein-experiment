package com.einstein.experiment.spring;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * @author liujiaming
 * @since 2017/03/08
 * <p>
 * "@Scheduled"使用总结：
 * 1. 显示配置task线程池，线程池（ThreadPoolTaskExecutor）默认corePoolSize=1
 * 2. 多任务时，应使用@Async注解提交任务到线程池，否则任务占用主线程可能导致任务执行延迟
 */
public class ScheduledService {

    private static int count;

//    @Async
    @Scheduled(cron = "*/2 * * * * *")
    public void ScheduledTask1() {
        System.out.println("Task start " + count++ + " @" + new Date() + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    @Async
    @Scheduled(cron = "*/2 * * * * *")
    public void ScheduledTask2() {
        System.out.println("Task start " + count++ + " @" + new Date() + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
