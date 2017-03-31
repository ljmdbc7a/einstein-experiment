package com.einstein.experiment;

import com.einstein.experiment.utils.HttpUtils;

public class Base {

    public static void main(String[] args) {
        //        List<String> a = new ArrayList<String>();
        //        a.add("1");
        //        a.add("2");
        //        for (String temp : a) {
        //            if ("2".equals(temp)) {
        //                a.remove(temp);
        //            }
        //        }
        //        System.out.println(a);

        //        boolean b = true;
        //        if(b == Boolean.TRUE){
        //            System.out.println("xxxxx");
        //        }

        //        String a = null;
        //        if ("11".equals(a)) {
        //            System.out.println("aaa");
        //        }

        //        long start1 = System.currentTimeMillis();
        //        for (int i = 0; i < 1000000; i++) {
        //            Object obj = new Object();
        //        }
        //        System.out.println(System.currentTimeMillis() - start1);
        //
        //        long start = System.currentTimeMillis();
        //        for (int i = 0; i < 1000000; i++) {
        //            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy");
        //        }
        //        System.out.println(System.currentTimeMillis() - start);

        String url = args[0];
        if (url == null) {
            url = "http://idc01-wdin-web-vip00/wd/item/base/getBaseInfoByIds?param=%7B%22item_ids%22:%221785693522,1785684381%22%7D";
        }
        String s = HttpUtils.doHttpGet(url);
        System.out.println(s);

    }
}
