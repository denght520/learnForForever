package com.learn.forever.core.listener;

import com.alibaba.fastjson.JSON;
import com.learn.forever.core.annotation.ParamCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @projectName: learnForForever
 * @className: DemoListener
 * @author: denghaitao
 * @date: 2020/1/15
 * @version: 1.0
 * @E-mail: denghaitao@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
@Service
public class DemoListener implements Listener{

    @Override
    @ParamCheck
    public Integer dealListenerEvent(Object listEvent) {
        System.out.println("监听器："+ JSON.toJSONString(listEvent));
        return 11;
    }
}
