package com.learn.forever.core.mq.consumer;

import com.bluewhale.constant.mq.MqConstant;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @projectName: learnForForever
 * @className: MessagerListener
 * @author: denghaitao
 * @date: 2020/1/6
 * @version: 1.0
 * @E-mail: denghaitao@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
public class MessagerListener implements MessageListenerOrderly {
    @Override
    public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
        for (MessageExt messageExt : list){
            String tags = messageExt.getTags();
            String topic = messageExt.getTopic();
            switch (topic){
                case MqConstant.TOPIC_ORDER_DETAIL:
                    if(MqConstant.TAG_ORDER_SETTLE_READY.equals(tags)){

                    }
            }
        }
        return ConsumeOrderlyStatus.SUCCESS;
    }
}
