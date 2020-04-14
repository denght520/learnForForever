package com.learn.forever.core.util;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * 自身内容能以可读方式输出
 */
public abstract class PrintFriendliness implements Serializable {

    /**
     * 序列化版本
     */
    private static final long serialVersionUID = -8504829055186474478L;

    @Override
    public String toString() {
        try {
            return this.getClass().getSimpleName() + ":" + JSON.toJSONStringWithDateFormat(this, "yyyy-MM-dd HH:mm:ss.SSS");
        } catch (Exception e) {
            return "NULL";
        }
    }


}
