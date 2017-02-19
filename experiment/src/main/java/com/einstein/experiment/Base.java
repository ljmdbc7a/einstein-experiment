package com.einstein.experiment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liujiaming on 2017/01/19 15:02.
 */
public class Base {

    public static void main(String[] args) {
        List<String> a = new ArrayList<String>();
        a.add("1");
        a.add("2");
        for (String temp : a) {
            if ("2".equals(temp)) {
                a.remove(temp);
            }
        }
        System.out.println(a);
    }
}
