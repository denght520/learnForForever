package com.learn.forever.core.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.learn.forever.core.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisListCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author : denghaitao
 * @version 1.0
 * @projectName：bw-union
 * @className：RedisServiceImpl
 * @date 2019-12-20 11:49
 * @E-mail:gongdexing@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    private final static String UTF8 = "UTF-8";

    /**
     * @Description: 设置key/value
     */
    public void set(String key, String value, long liveTime) {
        this.set(getRedisSerializer().serialize(key), getRedisSerializer().serialize(value),
                liveTime);
    }

    /**
     * @throws @author lvxijin
     * @Description: hash存储
     */
    @SuppressWarnings("unchecked")
    public <T> void hmset(final String key, T t) {
        final HashMap<byte[], byte[]> data = new HashMap<byte[], byte[]>();
        JSONObject json = JSONObject.parseObject(JSONObject.toJSONString(t));
        Set<String> its = json.keySet();
        for (String ik : its) {
            String value = String.valueOf(json.get(ik));
            data.put(getRedisSerializer().serialize(ik), getRedisSerializer().serialize(value));
        }
        redisTemplate.execute(new RedisCallback<Object>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.hMSet(key.getBytes(), data);
                return 1L;
            }
        });
    }


    /**
     * @throws @author lvxijin
     * @Description: hash删除
     */
    @SuppressWarnings("unchecked")
    public <T> void hdel(final String key, final String... fields) {
        final byte[][] b = new byte[fields.length][];
        int i = 0;
        for (String str : fields) {
            if (StringUtils.isNotBlank(str)) {
                b[i] = str.getBytes();
            }
            i++;
        }
        redisTemplate.execute(new RedisCallback<Object>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.hDel(key.getBytes(), b);
                return 1L;
            }
        });
    }

    /**
     * @throws @author 默认删除全部重复的
     * @Description: list删除
     */
    @SuppressWarnings("unchecked")
    public <T> void ldel(final String key, final String... values) {
        for (String field : values) {
            if (StringUtils.isBlank(field)) {
                continue;
            }
            redisTemplate.execute(new RedisCallback<Object>() {
                public Long doInRedis(RedisConnection connection) throws DataAccessException {
                    connection.lRem(key.getBytes(), 0, field.getBytes());
                    return 1L;
                }
            });

        }
    }


    /**
     * @throws @author lvxijin
     * @Description: redis锁获取
     */
    @SuppressWarnings("unchecked")
    public Boolean tryLock(final String key, final String value, final Long time) {

        return (Boolean) redisTemplate.execute(new RedisCallback<Object>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {

                boolean flag = false;
                if (StringUtils.isBlank(get(key))) {
                    flag = true;
                }
                Boolean lock = connection.setNX(getRedisSerializer().serialize(key),
                        getRedisSerializer().serialize(value));
                if (time != null && flag) {
                    expire(key, time);
                }
                return lock;
            }
        });
    }

    public void unLock(final String key) {
        delete(key);
    }

    /**
     * @throws @author lvxijin
     * @Description: set过期时间
     */
    @SuppressWarnings("unchecked")
    public void expire(final String key, final Long time) {
        redisTemplate.execute(new RedisCallback<Object>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.expire(getRedisSerializer().serialize(key), time);
                return 1L;
            }
        });
    }


    /**
     * @throws @author lvxijin
     * @Description: hash单个属性
     */
    @SuppressWarnings("unchecked")
    public void hset(final String key, final String field, final String value) {
        redisTemplate.execute(new RedisCallback<Object>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.hSet(getRedisSerializer().serialize(key),
                        getRedisSerializer().serialize(field),
                        getRedisSerializer().serialize(value));
                return 1L;
            }
        });
    }


    /**
     * @throws @author lvxijin
     * @Description: hash获取单个属性
     */
    @SuppressWarnings("unchecked")
    public String hget(final String key, final String fields) {
        return (String) redisTemplate.execute(new RedisCallback<Object>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    byte[] bystr = connection.hGet(getRedisSerializer().serialize(key),
                            getRedisSerializer().serialize(fields));
                    return bystr == null ? null : new String(bystr, UTF8);
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return null;
            }
        });
    }

    /**
     * @throws @author lvxijin
     * @Description: hash
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> hGetAll(final String key) {
        return (Map<String, String>) redisTemplate.execute(new RedisCallback<Object>() {
            public Map<String, String> doInRedis(RedisConnection connection)
                    throws DataAccessException {
                try {
                    Map<byte[], byte[]> by = connection
                            .hGetAll(getRedisSerializer().serialize(key));
                    Map<String, String> mapStr = new HashMap<String, String>();
                    Set<byte[]> set = by.keySet();
                    for (byte[] bt : set) {
                        mapStr.put(new String(bt, UTF8), new String(by.get(bt), UTF8));
                    }
                    return mapStr;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }

    /**
     * @param key 主键
     * @param fields 多个属性，数组形式
     * @throws @author lvxijin
     * @Description: hash获取多个属性
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> hmget(final String key, final String... fields) {
        final byte[][] b = new byte[fields.length][];
        int i = 0;
        for (String str : fields) {
            if (StringUtils.isBlank(str)) {
                b[i] = str.getBytes();
            }
            i++;
        }
        return (Map<String, String>) redisTemplate.execute(new RedisCallback<Object>() {
            public Map<String, String> doInRedis(RedisConnection connection)
                    throws DataAccessException {
                try {
                    List<byte[]> list = connection.hMGet(getRedisSerializer().serialize(key), b);
                    Map<String, String> map = new HashMap<String, String>();
                    int j = 0;
                    for (byte[] by : list) {
                        if (by != null) {
                            map.put(fields[j], new String(by, UTF8));
                        }
                        j++;
                    }
                    return map;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }


    /**
     * @throws @author lvxijin
     * @Description: 设置key/value
     */
    public void set(String key, String value) {
        this.set(key, value, 0L);
    }

    /**
     * @throws @author lvxijin
     * @Description: 设置key/value
     */
    public void set(byte[] key, byte[] value) {
        this.set(key, value, 0L);
    }

    /**
     * @throws @author lvxijin
     * @Description: 设置key/value
     */
    @SuppressWarnings("unchecked")
    public void set(final byte[] key, final byte[] value, final long liveTime) {
        redisTemplate.execute(new RedisCallback<Object>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key, value);
                if (liveTime > 0) {
                    connection.expire(key, liveTime);
                }
                return 1L;
            }
        });
    }

    /**
     * @throws @author lvxijin
     * @Description: 删除key/value
     */
    @SuppressWarnings("unchecked")
    public void delete(String... keys) {
        redisTemplate.delete(Arrays.asList(keys));
    }

    /**
     * @throws @author lvxijin
     * @Description: 删除key/value
     */
    @SuppressWarnings("unchecked")
    public <T> void delete(List<T> lkey) {
        redisTemplate.delete(lkey);
    }

    /**
     * @throws @author lvxijin
     * @Description: 删除key/value
     */
    @SuppressWarnings("unchecked")
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * @Description: 获取value返回为string类型
     * @author lvxijin
     */
    @SuppressWarnings("unchecked")
    public String get(final String key) {
        return (String) redisTemplate.execute(new RedisCallback<Object>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    byte[] by = connection.get(key.getBytes());
                    if (by == null) {
                        return null;
                    } else {
                        return new String(by, UTF8);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }


    /**
     * @Description: 获取多个key的value
     * @author lvxijin
     */
    @SuppressWarnings("unchecked")
    public List<String> mget(final String... keys) {
        final byte[][] b = new byte[keys.length][];
        int i = 0;
        for (String str : keys) {
            if (StringUtils.isBlank(str)) {
                b[i] = str.getBytes();
            }
            i++;
        }
        return (List<String>) redisTemplate.execute(new RedisCallback<Object>() {
            public List<String> doInRedis(RedisConnection connection) throws DataAccessException {
                List<byte[]> lb = connection.mGet(b);
                List<String> ls = new ArrayList<String>();
                for (byte[] bt : lb) {
                    try {
                        ls.add(new String(bt, UTF8));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                return ls;
            }
        });
    }


    /**
     * @Description: 获取多个key和value
     * @author lvxijin
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> keysAndValues(final String pattern) {
        Set<String> set = redisTemplate.keys(pattern);
        Map<String, String> map = new HashMap<String, String>();
        for (String str : set) {
            if (StringUtils.isBlank(str)) {
                map.put(str, get(str));
            }
        }
        return map;
    }


    /**
     * @Description: 查看是否存在
     * @author lvxijin
     */
    @SuppressWarnings("unchecked")
    public Boolean exists(final String key) {
        return (Boolean) redisTemplate.execute(new RedisCallback<Object>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.exists(getRedisSerializer().serialize(key));
            }
        });
    }

    /**
     * @Description: 清空数据库 慎用
     * @author lvxijin
     */
    @SuppressWarnings("unchecked")
    public void flushDB() {
        redisTemplate.execute(new RedisCallback<Object>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return null;
            }
        });
    }

    /**
     * @Description: 数据库key数量
     * @author lvxijin
     */
    @SuppressWarnings("unchecked")
    public Long dbSize() {
        return (Long) redisTemplate.execute(new RedisCallback<Object>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.dbSize();
            }
        });
    }

    /**
     * @Description: 接通检测
     * @author lvxijin
     */
    @SuppressWarnings("unchecked")
    public String ping() {
        return (String) redisTemplate.execute(new RedisCallback<Object>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.ping();
            }
        });
    }

    /**
     * @Description: 递增序列
     * @author lvxijin
     */
    @SuppressWarnings("unchecked")
    public Long incr(final String key) {
        return (Long) redisTemplate.execute(new RedisCallback<Object>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.incr(getRedisSerializer().serialize(key));
            }
        });
    }

    /**
     * @description:递增指定数量
     * @author: huangziwang
     * @version: v0.1
     * @time: 2019年5月25日 下午4:32:34
     */
    @SuppressWarnings("unchecked")
    public Long incrby(final String key, long value) {
        return (Long) redisTemplate.execute(new RedisCallback<Object>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.incrBy(getRedisSerializer().serialize(key), value);
            }
        });
    }

    /**
     * @description:递减序列
     * @author: huangziwang
     * @version: v0.1
     * @time: 2019年5月25日 下午4:33:41
     */
    @SuppressWarnings("unchecked")
    public Long decr(final String key) {
        return (Long) redisTemplate.execute(new RedisCallback<Object>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.decr(getRedisSerializer().serialize(key));
            }
        });
    }

    /**
     * @description:递减指定数量
     * @author: huangziwang
     * @version: v0.1
     * @time: 2019年5月25日 下午4:32:34
     */
    @SuppressWarnings("unchecked")
    public Long decrBy(final String key, long value) {
        return (Long) redisTemplate.execute(new RedisCallback<Object>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.decrBy(getRedisSerializer().serialize(key), value);
            }
        });
    }

    /**
     * @Description: 序列化
     * @author lvxijin
     */
    @SuppressWarnings("unchecked")
    private RedisSerializer<String> getRedisSerializer() {
        return redisTemplate.getStringSerializer();
    }


    @Override
    public <T> void deleteAll(String pattern) {
        // TODO Auto-generated method stub

    }


    /**
     * @Description: 集合数据保存
     * @author lvxijin
     */

    @SuppressWarnings("unchecked")
    public Long lpush(final String key, final String... values) {
        if (!StringUtils.isNoneBlank(values)) {
            return null;
        }
        final byte[][] bvalue = new byte[values.length][];
        int i = 0;
        for (String str : values) {
            if (StringUtils.isNotBlank(str)) {
                bvalue[i] = str.getBytes();
            }
            i++;
        }
        return (Long) redisTemplate.execute(new RedisCallback<Object>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.rPush(getRedisSerializer().serialize(key), bvalue);
            }
        });
    }


    /**
     * @Description: 集合数据保存
     * @author lvxijin
     */

    @SuppressWarnings("unchecked")
    public List<String> lRange(final String key, final long begin, final long end) {
        return (List<String>) redisTemplate.execute(new RedisCallback<Object>() {
            public List<String> doInRedis(RedisConnection connection) throws DataAccessException {
                List<byte[]> by = connection
                        .lRange(getRedisSerializer().serialize(key), begin, end);
                List<String> li = new ArrayList<String>();
                for (byte[] b : by) {
                    try {
                        li.add(new String(b, UTF8));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                return li;
            }
        });
    }

    @SuppressWarnings("unchecked")
    public Long lInsert(final String key, final String pivot, final String value) {
        return (Long) redisTemplate.execute(new RedisCallback<Object>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.lInsert(getRedisSerializer().serialize(key),
                        RedisListCommands.Position.AFTER, getRedisSerializer().serialize(pivot),
                        getRedisSerializer().serialize(value));
            }
        });
    }

    @SuppressWarnings("unchecked")
    public void lClear(String key) {
        redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.lTrim(getRedisSerializer().serialize(key), 1L, 0L);
                return null;
            }
        });
    }


    /**
     * @Description: 集合总数保存
     * @author lvxijin
     */

    @SuppressWarnings("unchecked")
    public Long lLen(final String key) {
        return (Long) redisTemplate.execute(new RedisCallback<Object>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.lLen(getRedisSerializer().serialize(key));
            }
        });
    }

    /**
     * @param key hase的key
     * @param fields hase的field
     * @param delta 计数--正数的情况是递增，负数则递减
     * @description:hash计数
     * @author: huangziwang
     * @version: v0.1
     * @time: 2019年5月25日 下午2:40:53
     */
    @SuppressWarnings("unchecked")
    public Long hIncrBy(final String key, final String field, Long delta) {
        return (Long) redisTemplate.execute(new RedisCallback<Object>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                long hIncrBy = connection.hIncrBy(getRedisSerializer().serialize(key),
                        getRedisSerializer().serialize(field), delta);
                return hIncrBy;
            }
        });
    }

    @Override
    public void deleteByPrex(String keys) {
        Set<String> set = redisTemplate.keys(keys + "*");
        redisTemplate.delete(set);
    }

}
