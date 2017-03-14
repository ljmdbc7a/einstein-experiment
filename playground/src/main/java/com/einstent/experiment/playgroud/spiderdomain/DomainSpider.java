package com.einstent.experiment.playgroud.spiderdomain;

import com.einstein.experiment.utils.HttpUtils;
import com.einstein.experiment.utils.JsonUtils;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author liujiaming
 * @since 2017/03/06
 */
public class DomainSpider {

    private static final Executor executor = Executors.newFixedThreadPool(100);

    // worker
    static class Spider implements Runnable {

        private String url;

        private String domain;

        private String callback;

        private String token;

        public Spider(String url, String domain, String callback, String token) {
            this.url = url;
            this.domain = domain;
            this.callback = callback;
            this.token = token;
        }

        @Override
        public void run() {
            String response = HttpUtils.doHttpGet(new StringBuilder(url).append("domain=").append(domain).append("&callback=").append(callback).append("&token=").append(token).toString());
            if (response == null) {
                System.out.println(domain + " -1");
            } else {
                String jsonStr = response.substring(callback.length() + 1, response.length() - 2);
                WanwangResponse wanwangResponse = JsonUtils.deserialize(jsonStr, WanwangResponse.class);
                for (WanwangResponse.DomainStatus domainStatus : wanwangResponse.getModule()) {
//                    System.out.println(domainStatus.getName() + " " + domainStatus.getAvail());
                    File file = new File("/Users/liujiaming/Downloads/aaaa.out");
                    try {
                        Files.append(domainStatus.getName() + " " + domainStatus.getAvail() + "\n", file, Charset.defaultCharset());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        List<String> suffixs = new ArrayList<String>();
        suffixs.add(".com");
        suffixs.add(".me");
        suffixs.add(".cc");
        suffixs.add(".tech");
        File file = new File("/Users/liujiaming/Downloads/aaaa");
        try {
            List<String> list = Files.readLines(file, Charset.defaultCharset());
            for (String word : list) {
                for (String suffix : suffixs) {
                    Spider spider = new Spider("https://checkapi.aliyun.com/check/checkdomain?", word + suffix, "jQuery111109075044029601718_" + System.currentTimeMillis(), "check-web-hichina-com%3A3lf6hux35c5dwbet8d0wdd403k0dy1nm");
                    executor.execute(spider);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
