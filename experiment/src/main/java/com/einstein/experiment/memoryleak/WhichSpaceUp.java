package com.einstein.experiment.memoryleak;

import java.util.HashMap;
import java.util.Map;

public class WhichSpaceUp {

    private static Map<String, String> map = new HashMap<String, String>();

    public static void main(String[] args) {
        int loop = Integer.MAX_VALUE;
        if (args.length == 1) {
            loop = Integer.valueOf(args[0]);
        }

        String key = "";
        for (int i = 0; i < loop; i++) {
            key += i;
            //            key.intern();
            map.put(key.intern(), "");
            if (i % 1000 == 0) {
                System.out.println(map.size());
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            Thread.sleep(5 * 60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
