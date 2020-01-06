package com.learn.forever.core.config;

import com.learn.forever.core.mq.consumer.MessagerListener;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;

/**
 * @projectName: learnForForever
 * @className: MQConfiguration
 * @author: denghaitao
 * @date: 2020/1/6
 * @version: 1.0
 * @E-mail: denghaitao@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
@Configuration
public class MQConfiguration {

    @Value("${rocketmq.namesrvAddr}")
    private String namesrvAddr;

    @Value("${rocketmq.producer.group}")
    private String producerGroup;

    @Value("${rocketmq.producer.retryTimes}")
    private int retryTimes;

    @Value("${rocketmq.consumer.group}")
    private String consumerGroup;

    @Value("${rocketmq.order.consumer.topic}")
    private String consumerOrderTopic;

    @Value("${rocketmq.order.consumer.tags}")
    private String consumerOrderTags;

    private DefaultMQPushConsumer consumer;


    private DefaultMQProducer defaultMQProducer;

    @Bean
    public MessageListener messageListener(){
        return new MessagerListener();
    }

    @Bean
    public DefaultMQPushConsumer consumer() throws MQClientException {
        consumer = new DefaultMQPushConsumer();
        consumer.setConsumerGroup(consumerGroup);
        consumer.setNamesrvAddr(namesrvAddr);
        Map<String, String> subscribeMap = new HashMap<>();
        subscribeMap.put(consumerOrderTopic, consumerOrderTags);
        consumer.setSubscription(subscribeMap);
        consumer.setMessageListener(messageListener());
        consumer.start();
        return consumer;
    }

    @Bean
    public DefaultMQProducer producer() throws MQClientException {
        defaultMQProducer = new DefaultMQProducer();
        defaultMQProducer.setRetryTimesWhenSendAsyncFailed(retryTimes);
        defaultMQProducer.setNamesrvAddr(namesrvAddr);
        defaultMQProducer.setProducerGroup(producerGroup);
        defaultMQProducer.start();
        return defaultMQProducer;
    }


    @PreDestroy
    public void destory(){
        if(null != consumer){
            consumer.shutdown();
        }
        if(null != defaultMQProducer){
            defaultMQProducer.shutdown();
        }
    }
}
