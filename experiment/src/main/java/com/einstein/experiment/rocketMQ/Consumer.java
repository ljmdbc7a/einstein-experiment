package com.einstein.experiment.rocketMQ;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * RocketMQ消费者Demo。
 */
public class Consumer {

    public static void main(String[] args) throws MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test_group_2");

        // 队列服务地址
        consumer.setNamesrvAddr("10.1.15.80:9876");

        //设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费。如果非第一次启动，那么按照上次消费的位置继续消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        // 设置队列topic
        consumer.subscribe("AccountChange", "*");

//        File file = new File("/Users/liujiaming/Documents/code-open/einstein-experiment/experiment/src/main/java/com/einstein/experiment/rocketMQ/MessageFilterImpl.java");
//        String filterCode = MixAll.file2String(file);
//        consumer.subscribe("AccountChange", "com.einstein.experiment.rocketMQ.MessageFilterImpl.java", filterCode);

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println("Received msg's tags: " + msg.getTags());
                }
                System.out.println("Received msgs: " + msgs);
                System.out.println("Received msg's body: " + new String(msgs.get(0).getBody()));
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
        System.out.println("Consumer started.");
    }
}
