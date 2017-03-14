package com.einstein.experiment.rocketMQ;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

/**
 * RocketMQ生产者Demo。
 */
public class Producer {

    public static void main(String[] args) throws MQClientException {

        DefaultMQProducer producer = new DefaultMQProducer("test_group");

        // 设置队列服务地址
        producer.setNamesrvAddr("10.1.15.80:9876");

        // 只需启动时调用初始化一次
        producer.start();

        for (int i = 0; i < 10; i++) {
            Message message = new Message("AccountChange", // topic
                                          "tagsB",  // tags
                                          ("Hello RocketMQ " + i).getBytes() // body
            );
            try {
                SendResult sendResult = producer.send(message);
                System.out.println(sendResult);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 应用退出时，要调用shutdown来清理资源，关闭网络连接，从MetaQ服务器上注销自己
         * 注意：我们建议应用在JBOSS、Tomcat等容器的退出钩子里调用shutdown方法
         */
        producer.shutdown();
    }

}
