package com.einstein.experiment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liujiaming on 2017/02/17 10:11.
 */
public class Temp {
    public static Map<String, MyObject> map = new HashMap<String, MyObject>();

    public static void main(String[] args) {
        //=====================Begin=========================
        System.out.print("Xmx=");
        System.out.println(Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "M");


        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            map.put(String.valueOf(i), new MyObject());
            System.out.println(map.size());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("end");
    }

    public static class MyObject {
//        private byte[] bytes;
        MyObject() {
            byte[] bytes = new byte[1024 * 1024]; //1M
        }
    }
}
