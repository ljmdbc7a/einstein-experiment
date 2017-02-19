package com.einstein.experiment.memoryleak;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liujiaming on 2017/02/17 09:43.
 */
public class WhichSpaceUp {

    private static Map<MapKey, MapValue> map = new HashMap<MapKey, MapValue>();

    public static class MapKey {
        private String key;

        public MapKey(String key) {
            this.key = key;
        }
    }

    public static class MapValue {
        private String value;

        public MapValue(String value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("请输入参数");
            return;
        }

        int loop = Integer.valueOf(args[0]);

        for (int i = 0; i < loop; i++) {
            map.put(new MapKey("key-" + i), new MapValue("value-" + i));
        }

        try {
            Thread.sleep(5 * 60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
