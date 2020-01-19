package com.learn.forever.core.listener;

import com.learn.forever.core.damin.request.PersonReqDTO;

/**
 * @projectName: learnForForever
 * @className: Listener
 * @author: denghaitao
 * @date: 2020/1/15
 * @version: 1.0
 * @E-mail: denghaitao@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
public interface Listener<T> {

    /**
     * 处理监听事件
     */
    Integer dealListenerEvent(T listEvent);
}
