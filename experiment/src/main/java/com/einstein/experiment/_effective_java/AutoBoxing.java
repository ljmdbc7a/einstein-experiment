package com.einstein.experiment._effective_java;

/**
 * @author liujiaming
 * @since 2017/02/27
 * <p/>
 * 自动装箱创建对象
 */
public class AutoBoxing {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Long sum = 0L; // 装箱基本类型
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        System.out.println("Consume " + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        long sum1 = 0L; // 基本类型
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum1 += i;
        }
        System.out.println("Consume " + (System.currentTimeMillis() - start) + " ms");
    }
}
