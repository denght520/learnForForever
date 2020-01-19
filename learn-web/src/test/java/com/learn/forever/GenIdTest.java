package com.learn.forever;

import com.learn.forever.client.GenIdRpc;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @projectName: learnForForever
 * @className: GenIdTest
 * @author: denghaitao
 * @date: 2020/1/16
 * @version: 1.0
 * @E-mail: denghaitao@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
public class GenIdTest extends BaseTest{

    @Autowired
    private GenIdRpc genIdRpc;

    @Test
    public void 主键生成测试(){
        Long test = genIdRpc.getId("test");
        System.out.println(test);
    }
}
