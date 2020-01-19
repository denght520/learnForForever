//package com.learn.forever.service.impl;
//
//import com.learn.forever.damin.Person;
//import com.learn.forever.dao.EsDao;
//import com.learn.forever.service.EsService;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.Optional;
//
///**
// * @projectName: learnForForever
// * @className: EsServiceImpl
// * @author: denghaitao
// * @date: 2020/1/9
// * @version: 1.0
// * @E-mail: denghaitao@oxyzgroup.com
// * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
// * @return
// */
//@Service
//public class EsServiceImpl implements EsService {
//
//    @Resource
//    private EsDao esDao;
//
//    @Override
//    public void add(Person person) {
//        esDao.save(person);
//    }
//
//    @Override
//    public Optional findByUserId(Long userId) {
//        Optional byId = esDao.findById(userId);
//        return byId;
//    }
//}
