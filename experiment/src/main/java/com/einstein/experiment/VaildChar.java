package com.einstein.experiment;

import java.nio.charset.Charset;

/**
 *
 */
public class VaildChar {

    public static void main(String[] args) {
        char[] arr = new char[]{'￡'};
        String str = "e小调￡故事";
        byte[] chars = str.getBytes(Charset.forName("GBK"));// UTF-8
        for (int i = 0; i < chars.length; i++) {
            System.out.println(chars[i]);
        }
        System.out.println(chars);
    }

}
