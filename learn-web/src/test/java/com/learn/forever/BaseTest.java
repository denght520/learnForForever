package com.learn.forever;

import com.bluewhale.constant.mq.MqConstant;
import com.learn.forever.core.mq.Producer.MessageProducer;
import com.learn.forever.core.service.DemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

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

    @Autowired
    private DemoService demoService;

    @Test
    public void mq发送测试(){
        messageProducer.sendMq(MqConstant.TOPIC_SETTLE_NOTIFY, MqConstant.TAGS_NOTIFY_SETTLE, "测试");
    }

    @Test
    public void 时间类测试(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now="+now);
        LocalDate now1 = LocalDate.now();
        System.out.println("now1="+now1);
        LocalTime now2 = LocalTime.now();
        System.out.println("now2="+now2);
        LocalDateTime now3 = now.plusDays(5);
        System.out.println("now3="+now3);
        LocalDateTime plus = now.plus(1, ChronoUnit.WEEKS);
        System.out.println("plus="+plus);
        Instant now4 = Instant.now();
        System.out.println("now4="+now4);
        Date from = Date.from(now4);
        System.out.println("from="+from);
        System.out.println("now5="+new Date());
    }

    @Test
    public void 监听器测试(){
        demoService.testDemo();
    }
}
