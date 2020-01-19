//package com.learn.forever.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.TransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
///**
// * @projectName: learnForForever
// * @className: EsConfig
// * @author: denghaitao
// * @date: 2020/1/10
// * @version: 1.0
// * @E-mail: denghaitao@oxyzgroup.com
// * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
// * @return
// */
//@Configuration
//@Slf4j
//public class EsConfig {
//
//    @Bean
//    public TransportClient transportClient() {
//
//        log.info("初始化开始。。。。。");
//
//        TransportClient client = null;
//
//        TransportAddress master = null;
//
//        try {
//            master = new TransportAddress(
//                    InetAddress.getByName("esapi.bw365.net"), Integer.valueOf(9200));
//            Settings settings = Settings.builder()
//                    // 集群的名称
//                    .put("", "bw-es6-dev")
//                    // 自动发现节点
//                    .put("client.transport.sniff", true)
//                    .build();
//
//            client = new PreBuiltTransportClient(settings)
//                    .addTransportAddress(master);
//        } catch(UnknownHostException e){
//            e.printStackTrace();
//            log.info("初始化bean失败");
//        }
//
//        return client;
//    }
//}
