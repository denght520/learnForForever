package com.learn.forever.core.service;

import java.util.List;
import java.util.Map;

/**
 * @author : denghaitao
 * @version 1.0
 * @projectName：bw-union
 * @className：RedisService
 * @date 2019-12-20 11:48
 * @E-mail:gongdexing@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
public interface RedisService {

    /**
     * @throws @author Administrator
     * @Description: set过期时间
     */
    void expire(final String key, final Long time);

    /**
     * @throws @author Administrator
     * @Description: 设置key/value
     */
    void set(String key, String value, long liveTime);

    /**
     * @throws @author Administrator
     * @Description: 设置key/value
     */
    void set(String key, String value);

    /**
     * @throws @author Administrator
     * @Description: 设置key/value
     */
    void set(byte[] key, byte[] value);

    /**
     * @throws @author lvxijin
     * @Description: hash删除
     */
    <T> void hdel(final String key, final String... fields);

    /**
     * @throws @author Administrator
     * @Description: 设置key/value
     */
    void set(final byte[] key, final byte[] value, final long liveTime);

    /**
     * @throws @author Administrator
     * @Description: 删除key/value
     */
    void delete(String... keys);

    /**
     * @throws @author count > 0：删除相当于value从头到尾移动的元素。 count < 0：删除等于value从尾部移动到头部的元素。 count =
     * 0：删除所有等于的元素value。
     * @Description: list删除
     */
    <T> void ldel(final String key, final String... values);


    /**
     * @throws @author Administrator
     * @Description: 删除key/value
     */
    <T> void delete(List<T> lkey);

    /**
     * @throws @author Administrator
     * @Description: 删除模糊匹配删除
     */
    <T> void deleteAll(String pattern);

    /**
     * @throws @author Administrator
     * @Description: 删除key/value
     */
    void delete(String key);

    /**
     * @Description: 获取value返回为string类型
     * @author Administrator
     */
    String get(final String key);

    /**
     * @Description: 查看是否存在
     * @author Administrator
     */
    Boolean exists(final String key);

    /**
     * @Description: 清空数据库 慎用
     * @author Administrator
     */
    void flushDB();

    /**
     * @Description: 数据库key数量
     * @author Administrator
     */
    Long dbSize();

    /**
     * @Description: 接通检测
     * @author Administrator
     */
    String ping();

    /**
     * @Description: 递增序列
     * @author Administrator
     */
    Long incr(final String key);

    /**
     * @description:递增指定数量
     * @author: huangziwang
     * @version: v0.1
     * @time: 2019年5月25日 下午4:32:34
     */
    Long incrby(final String key, long value);

    /**
     * @description:递减序列
     * @author: huangziwang
     * @version: v0.1
     * @time: 2019年5月25日 下午4:33:41
     */
    Long decr(final String key);

    /**
     * @description:递减指定数量
     * @author: huangziwang
     * @version: v0.1
     * @time: 2019年5月25日 下午4:32:34
     */
    Long decrBy(final String key, long value);

    /**
     * @Description: 获取多个key的value
     * @author Administrator
     */
    List<String> mget(final String... keys);

    /**
     * @Description: 获取多个key和value
     * @author Administrator
     */
    Map<String, String> keysAndValues(final String pattern);

    /**
     * @description: 在列表中添加一个或多个值
     * @return:
     * @author : neling chen
     * @date: 2019/7/1 11:33
     */
    Long lpush(final String key, final String... values);


    /**
     * 模糊删除 key+*
     */
    void deleteByPrex(String keys);


    /**
     * @description: 获取列表指定范围内的元素
     * @return:
     * @author : neling chen
     * @date: 2019/7/1 11:31
     */
    List<String> lRange(final String key, final long begin, final long end);

    /**
     * @description: 在列表的元素后插入元素
     * @return:
     * @author : neling chen
     * @date: 2019/7/1 11:39
     */
    Long lInsert(final String key, final String pivot, final String value);

    /**
     * @description: 清空列表
     * @return:
     * @author : neling chen
     * @date: 2019/7/1 11:39
     */
    void lClear(final String key);

    /**
     * @description: 获取列表数量
     * @return:
     * @author : neling chen
     * @date: 2019/7/1 11:31
     */
    Long lLen(final String key);

    /**
     * @throws @author Administrator
     * @Description: hash存储
     */
    <T> void hmset(final String key, T t);

    /**
     * @throws @author Administrator
     * @Description: hash获取
     */
    String hget(final String key, final String fields);

    /**
     * @throws @author Administrator
     * @Description: hash获取多个属性
     */
    Map<String, String> hmget(final String key, final String... fields);

    /**
     * @throws @author Administrator
     * @Description: hash获取全部
     */
    Map<String, String> hGetAll(final String key);


    /**
     * @throws @author Administrator
     * @Description: hash单个属性
     */
    void hset(final String key, final String field, final String value);

    /**
     * @throws @author Administrator
     * @Description: redis锁获取
     */
    Boolean tryLock(final String key, final String value, final Long time);

    /**
     * @throws @author Administrator
     * @Description: redis解锁
     */
    void unLock(final String key);

    /**
     * @param key hase的key
     * @param field hase的field
     * @param delta 计数--正数的情况是递增，负数则递减
     * @description:hash计数
     * @author: huangziwang
     * @version: v0.1
     * @time: 2019年5月25日 下午2:40:53
     */
    Long hIncrBy(final String key, final String field, Long delta);
}

