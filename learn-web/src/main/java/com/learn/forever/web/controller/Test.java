package com.learn.forever.web.controller;

import com.learn.forever.core.service.RedisService;
import com.learn.forever.core.spi.SeedSpi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.Resources;

/**
 * @projectName: learnForForever
 * @className: Test
 * @author: denghaitao
 * @date: 2020/1/4
 * @version: 1.0
 * @E-mail: denghaitao@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
@RestController
@RequestMapping("test")
@Slf4j
public class Test {

    @Autowired
    private SeedSpi seedSpi;

    @Value("${test.value}")
    private Integer testValue;

    @Autowired
    private RedisService redisService;

    @GetMapping("test")
    public Object test(){
        Long id = seedSpi.getId();
        log.info("[===============>id= {}]", id);
        redisService.set("idTest", id.toString());
        String idTest = redisService.get("idTest");
        return idTest;
    }

    @GetMapping("testValue")
    public Object test1(){
        return testValue;
    }
}
