package com.einstein.experiment.memoryleak.jdbc;

import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Field;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author liujiaming
 * @since 2017/03/14
 *
 */
public class NonRegisteringDriverTest {

    static final String driver = "com.mysql.jdbc.Driver";
    static final String dbName = "test";
    static final String userName = "root";
    static final String password = "android";
    static final String url = "jdbc:mysql://127.0.0.1:3306/" + dbName;

    public static class ConnectionTask implements Runnable {

        @Override
        public void run() {

            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 1000000; i++) {
                try {
                    java.sql.Connection conn = DriverManager.getConnection(url, userName, password);
                    //                    System.out.println(conn + Thread.currentThread()
                    //                                                    .getName());

                    // 关闭链接对象
                    if (conn != null) {
                        //                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        // 监控Driver实例中的 map 和 queue
        monitorNonRegisteringDriver();

        // 提交任务
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new ConnectionTask());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static void monitorNonRegisteringDriver() {
        Field field = null;
        try {
            // 通过反射获取 NonRegisteringDriver.class 的 map queue
            Class<?> clazz = Class.forName("com.mysql.jdbc.NonRegisteringDriver");
            field = clazz.getDeclaredField("connectionPhantomRefs");
            field.setAccessible(true);
            final ConcurrentHashMap connectionPhantomRefs = (ConcurrentHashMap) field.get(clazz);
            field = clazz.getDeclaredField("refQueue");
            field.setAccessible(true);
            ReferenceQueue refQueue = (ReferenceQueue) field.get(clazz);
            field = ReferenceQueue.class.getDeclaredField("queueLength");
            field.setAccessible(true);
            final Long queueLength = (Long) field.get(refQueue);

            // 监控 DriverManager NonRegisteringDriver
            ScheduledExecutorService monitorExecutor = Executors.newScheduledThreadPool(1);
            monitorExecutor.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    System.out.println(connectionPhantomRefs.size() + " connectionPhantomRefs.size(), queueLength: " + queueLength);
                }
            }, 0, 1000, TimeUnit.MILLISECONDS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
