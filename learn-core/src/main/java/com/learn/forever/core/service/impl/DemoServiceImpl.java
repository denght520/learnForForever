package com.learn.forever.core.service.impl;

import com.learn.forever.core.damin.request.PersonReqDTO;
import com.learn.forever.core.listener.DemoListener;
import com.learn.forever.core.listener.Listener;
import com.learn.forever.core.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @projectName: learnForForever
 * @className: DemoServiceImpl
 * @author: denghaitao
 * @date: 2020/1/15
 * @version: 1.0
 * @E-mail: denghaitao@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoListener demoListener;

    @Override
    public Integer testDemo(){
        PersonReqDTO personReqDTO = new PersonReqDTO();
        personReqDTO.setId(1L);
        personReqDTO.setUserName("test");
        personReqDTO.setAge(1);
        personReqDTO.setSex(2);
//        personReqDTO.setUserId(1L);
        personReqDTO.addListevent(demoListener);
        personReqDTO.deal();
        System.out.println("主流执行完成");
        return 1;
    }
}
