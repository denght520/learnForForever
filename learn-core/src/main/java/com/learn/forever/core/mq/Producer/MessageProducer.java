package com.learn.forever.core.mq.Producer;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @projectName: learnForForever
 * @className: MessageProducer
 * @author: denghaitao
 * @date: 2020/1/6
 * @version: 1.0
 * @E-mail: denghaitao@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
@Slf4j
@Service
public class MessageProducer {

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    /**
     * 同步发送
     * @param topic
     * @param tag
     * @param mesg
     */
    public void sendMq(String topic, String tag, String mesg){
        Message message = new Message();
        message.setTopic(topic);
        message.setTags(tag);
        message.setBody(JSON.toJSONBytes(mesg));
        try {
            defaultMQProducer.send(message);
            log.info("[mq send success topic= {}, tag= {}, mesg= {}]", topic, tag, mesg);
        }catch (Exception e) {
            log.error("[mq send fail topic= {}, tag= {}, mesg= {}]", topic, tag, mesg);
            e.printStackTrace();
        }
    }

    /**
     * 异步发送
     */
    public void sendMqAsyn(String topic, String tag, String mesg){
        Message message = new Message();
        message.setTopic(topic);
        message.setTags(tag);
        message.setBody(JSON.toJSONBytes(mesg));
        try {
            defaultMQProducer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info("[mq send success topic= {}, tag= {}, mesg= {}]", topic, tag, mesg);
                }

                @Override
                public void onException(Throwable throwable) {
                    log.error("[mq send fail topic= {}, tag= {}, mesg= {},reason= {}]", topic, tag, mesg, throwable.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
