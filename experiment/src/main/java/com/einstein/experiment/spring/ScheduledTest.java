package com.einstein.experiment.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author liujiaming
 * @since 2017/03/08
 */
public class ScheduledTest {

    public static void main(String[] args) {
        // 加载 spring 配置
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-bean-config.xml");

        ScheduledService scheduledService = (ScheduledService) context.getBean("scheduledService");
        System.out.println(scheduledService);
    }
}
