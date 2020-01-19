package com.learn.forever.client;

/**
 * @projectName: learnForForever
 * @className: GenIdRpc
 * @author: denghaitao
 * @date: 2020/1/16
 * @version: 1.0
 * @E-mail: denghaitao@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
public interface GenIdRpc {

    /**
     * 获取唯一主键
     */
    Long getId(String str);
}
