package com.learn.forever.core.service.impl;

import com.bluewhale.seed.client.SeedRpc;
import com.learn.forever.core.spi.SeedSpi;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;

/**
 * @projectName: learnForForever
 * @className: SeedSpi
 * @author: denghaitao
 * @date: 2020/1/4
 * @version: 1.0
 * @E-mail: denghaitao@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
@Service
public class SeedSpiImpl implements SeedSpi {

    @Reference
    private SeedRpc seedRpc;

    @Override
    public Long getId(){
        Long gId = seedRpc.getGId("learn-forever");
        return gId;
    }
}
