package com.learn.forever.core.sensorsdata;

import com.sensorsdata.analytics.javasdk.SensorsAnalytics;
import com.sensorsdata.analytics.javasdk.exceptions.InvalidArgumentException;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @projectName: learnForForever
 * @className: SenSors
 * @author: denghaitao
 * @date: 2020/1/14
 * @version: 1.0
 * @E-mail: denghaitao@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
public class SenSors {


    public static void main(String[] args) {

        try {
            final SensorsAnalytics sa = new SensorsAnalytics(
                    new SensorsAnalytics.ConcurrentLoggingConsumer("access.log"));
            String cookieId = "ABCDEF123456789";

            Map<String, Object> properties = new HashMap<String, Object>();
            properties.clear();
            properties.put("$time", new Date()); // 这条event发生的时间，如果不设置的话，则默认是当前时间
            properties.put("$os", "Windows"); // 通过请求中的UA，可以解析出用户使用设备的操作系统是windows的
            properties.put("$os_version", "8.1"); // 操作系统的具体版本
            properties.put("$ip", "123.123.123.123"); // 请求中能够拿到用户的IP，则把这个传递给SA，SA会自动根据这个解析省份、城市
            properties.put("Channel", "baidu"); // 用户是通过baidu这个渠道过来的
            sa.track(cookieId, false, "ViewHomePage", properties); // 记录访问首页这个event
            sa.flush();
            sa.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }
    }

}
