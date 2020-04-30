package com.learn.forever.config;


import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @Title: ElasticsearchConfig
 * @Description: Elasticsearch 配置类
 * @author chy
 * @date 2018/4/23 14:53
 */
@Configuration
public class ElasticsearchConfig {

    @Value("${elasticsearch.host}")
    private String host;
    @Value("${elasticsearch.port}")
    private Integer port;
    private String schema = "http";
    private int connectTimeOut = 1000;
    private int socketTimeOut = 30000;
    private int connectionRequestTimeOut = 500;
    private int maxConnectNum = 100;
    private int maxConnectPerRoute = 100;
    private HttpHost httpHost;
    private boolean uniqueConnectTimeConfig = true;
    private boolean uniqueConnectNumConfig = true;
    private RestClientBuilder builder;
    private RestHighLevelClient client;

    /**
     * Bean name default  函数名字
     *
     * @return
     */
    @Bean(autowire = Autowire.BY_NAME, name = "restHighLevelClient")
    public RestHighLevelClient client() {
        httpHost = new HttpHost(host, port, schema);
        builder = RestClient.builder(httpHost);
        if (uniqueConnectTimeConfig) {
            setConnectTimeOutConfig();
        }
        if (uniqueConnectNumConfig) {
            setMutiConnectConfig();
        }
        client = new RestHighLevelClient(builder);
        return client;
    }

    /**
     * 异步httpclient的连接延时配置
     */
    public void setConnectTimeOutConfig() {
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(connectTimeOut);
            requestConfigBuilder.setSocketTimeout(socketTimeOut);
            requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeOut);
            return requestConfigBuilder;
        });
    }


    /**
     * 异步httpclient的连接数配置
     */
    public void setMutiConnectConfig() {
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(maxConnectNum);
            httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
            return httpClientBuilder;
        });
    }

    /**
     * 关闭连接
     */
    public void close() {
        if (client != null) {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
