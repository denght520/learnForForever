package com.learn.forever.core.config;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @projectName: learnForForever
 * @className: ElasticJobConfig
 * @author: denghaitao
 * @date: 2020/1/7
 * @version: 1.0
 * @E-mail: denghaitao@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
@Configuration
public class ElasticJobConfiguration {

    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter elasticJob(@Value("${zookeeperAddress}")String zookeeperAddree,  @Value("${nameSpace}")String nameSpace){
        return new ZookeeperRegistryCenter(new ZookeeperConfiguration(zookeeperAddree, nameSpace));
    }
}
