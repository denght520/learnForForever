package com.learn.forever.core.service.impl;

import com.google.common.collect.Lists;
import com.learn.forever.client.GenIdRpc;
import com.sankuai.inf.leaf.common.Result;
import com.sankuai.inf.leaf.common.Status;
import com.sankuai.inf.leaf.snowflake.SnowflakeIDGenImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * @projectName: learnForForever
 * @className: GenIdRpcImpl
 * @author: denghaitao
 * @date: 2020/1/16
 * @version: 1.0
 * @E-mail: denghaitao@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
@Service
public class GenIdRpcImpl implements GenIdRpc {

    private static SnowflakeIDGenImpl snowflakeIDGen = null;
    private static String lock = "";

    @Value("${zkAddress}")
    private String zkAddress;

    @Value("${zkPort}")
    private int zkPort;

    public GenIdRpcImpl() {

    }

    /**
     * 懒汉模式单例
     * */
    private synchronized SnowflakeIDGenImpl getInstance() {
        if (null == snowflakeIDGen) {
            synchronized (lock) {
                if (null == snowflakeIDGen) {
                    SnowflakeIDGenImpl temp = new SnowflakeIDGenImpl(zkAddress, zkPort);
                    snowflakeIDGen =temp;
                }
            }
        }
        return snowflakeIDGen;
    }

    @Override
    public Long getId(String bizSrc) {
        if (StringUtils.isBlank(bizSrc)) {
            return System.currentTimeMillis();
        }
        SnowflakeIDGenImpl sf = getInstance();
        Result r = sf.get(bizSrc);
        if (r.getStatus().equals(Status.SUCCESS)) {
            return r.getId();
        }
        return System.currentTimeMillis();
    }

    /**
     * 线程安全
     */
    public synchronized List<Long> getBatchGId(String bizSrc, int size) {
        List<Long> retList = Lists.newArrayList();
        if (size < 1 || size > 10000 || StringUtils.isBlank(bizSrc)) {
            return retList;
        }

        SnowflakeIDGenImpl sf = getInstance();
        for (int i = 0; i < size; i++) {
            retList.add(sf.get(bizSrc).getId());
        }
        return retList;
    }

    public String getZkAddress() {
        return zkAddress;
    }

    public void setZkAddress(String zkAddress) {
        this.zkAddress = zkAddress;
    }

    public int getZkPort() {
        return zkPort;
    }

    public void setZkPort(int zkPort) {
        this.zkPort = zkPort;
    }


}
