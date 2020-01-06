package com.learn.forever;

import com.bluewhale.constant.mq.MqConstant;
import com.learn.forever.core.mq.Producer.MessageProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @projectName: learnForForever
 * @className: BaseTest
 * @author: denghaitao
 * @date: 2020/1/6
 * @version: 1.0
 * @E-mail: denghaitao@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
@SpringBootTest
public class BaseTest {

    @Autowired
    private MessageProducer messageProducer;

    @Test
    public void mq发送测试(){
        messageProducer.sendMq(MqConstant.TOPIC_SETTLE_NOTIFY, MqConstant.TAGS_NOTIFY_SETTLE, "测试");
    }
}
