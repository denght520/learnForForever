//package com.learn.forever.core.config;
//
//import com.dangdang.ddframe.job.api.simple.SimpleJob;
//import com.dangdang.ddframe.job.config.JobCoreConfiguration;
//import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
//import com.dangdang.ddframe.job.lite.api.JobScheduler;
//import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
//import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
//import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @projectName: learnForForever
// * @className: OrderJob
// * @author: denghaitao
// * @date: 2020/1/7
// * @version: 1.0
// * @E-mail: denghaitao@oxyzgroup.com
// * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
// * @return
// */
//@Configuration
//public class OrderJob {
//
//    @Autowired
//    private ZookeeperRegistryCenter zookeeperRegistryCenter;
//
//    @Value("${cron}")
//    private String cron;
//
//    @Bean
//    public SimpleJob orderJobTask(){
//        return new TestJob();
//    }
//
//    @Bean(initMethod = "init")
//    public JobScheduler simpleJobScheduler(final SimpleJob simpleJob){
//        return new SpringJobScheduler(simpleJob, zookeeperRegistryCenter, getLiteJobConfiguration(simpleJob.getClass(),
//                cron, 1, null ,null));
//    }
//
//    private LiteJobConfiguration getLiteJobConfiguration(final Class<? extends SimpleJob> jobClass,
//                                                         final String cron,
//                                                         final int shardingTotalCount,
//                                                         final String shardingItemParameters,
//                                                         final String jobParameters) {
//        // 定义作业核心配置
//        JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration
//                .newBuilder(jobClass.getName(), cron, shardingTotalCount).
//                        shardingItemParameters(shardingItemParameters).jobParameter(jobParameters).build();
//        // 定义SIMPLE类型配置
//        SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(simpleCoreConfig,
//                jobClass.getCanonicalName());
//        // 定义Lite作业根配置
//        LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).overwrite(true)
//                .build();
//        return simpleJobRootConfig;
//
//    }
//}
