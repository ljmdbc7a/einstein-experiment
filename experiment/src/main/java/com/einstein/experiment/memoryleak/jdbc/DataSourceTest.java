package com.einstein.experiment.memoryleak.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author liujiaming
 * @since 2017/03/15
 *
 */
public class DataSourceTest {

    public static String driver = "com.mysql.jdbc.Driver";
    public static String dbName = "test";
    public static String userName = "root";
    public static String password = "android";
    public static String url = "jdbc:mysql://127.0.0.1:3306/" + dbName + "?autoReconnect=true";

    public static int minIdle = 1000;
    public static int maxIdle = 1000;
    public static int initialSize = 100;
    public static int maxLifeTime = 8;

    public static DataSource initTomcatJDBC() {
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        // dataSource.setMaxActive(50);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxIdle);
        dataSource.setInitialSize(initialSize);
        dataSource.setTestOnBorrow(true);
        //        dataSource.setTestOnReturn(true);
        //        dataSource.setTestOnConnect(true);
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        System.out.println(dataSource.getPool()
                                     .getPoolProperties());
        return dataSource;
    }

    public static DataSource initHikariDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driver);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(userName);
        hikariConfig.setPassword(password);
        hikariConfig.setMaxLifetime(maxLifeTime);
        hikariConfig.setMinimumIdle(minIdle);
        hikariConfig.setMaximumPoolSize(maxIdle);
        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
        System.out.println("hikariDataSource.getDataSourceProperties(): " + hikariDataSource.getDataSourceProperties());
        return hikariDataSource;
    }

    public static void testDataSource(DataSource dataSource) {
        org.apache.tomcat.jdbc.pool.DataSource dataSource1 = (org.apache.tomcat.jdbc.pool.DataSource) dataSource;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            System.out.println(dataSource1.getPool()
                                          .getIdle() + " idle, active: " + dataSource1.getPool()
                                                                                      .getActive());
            try {
                Connection con = dataSource.getConnection();
                System.out.println("getConnection: " + con);
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        final org.apache.tomcat.jdbc.pool.DataSource dataSource = (org.apache.tomcat.jdbc.pool.DataSource) initTomcatJDBC();

        ExecutorService executorService = Executors.newFixedThreadPool(1000);

        for (int i = 0; i < 1000; i++) {
            executorService.execute(new Runnable() {
                public void run() {
                    testDataSource(dataSource);
                }
            });
        }
    }

}
