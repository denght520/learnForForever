package com.learn.forever.service.impl;

import com.alibaba.fastjson.JSON;
import com.learn.forever.damin.EsOperateDO;
import com.learn.forever.damin.Person;
import com.learn.forever.service.EsService;
import com.learn.forever.utils.ElasticsearchUtil;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * @projectName: learnForForever
 * @className: EsServiceImpl
 * @author: denghaitao
 * @date: 2020/1/9
 * @version: 1.0
 * @E-mail: denghaitao@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
@Service
public class EsServiceImpl implements EsService {

    @Override
    public void add(Person person) {
        EsOperateDO esOperateDO = new EsOperateDO();
        esOperateDO.setData(person);
        esOperateDO.setId(person.getId());
        esOperateDO.setIndexName("test-person");
        esOperateDO.setIndexType("test-rt");
        String s = ElasticsearchUtil.addData(esOperateDO);
        System.out.println(s);
    }

    @Override
    public Person findByUserId(Person person) {
        BoolQueryBuilder queryBuilder = buildQuery(person);
        Class<Person> personClass = Person.class;
        Person person1 = ElasticsearchUtil.searchOne(queryBuilder, Person.class, "test-person");
        return person1;
    }

    private BoolQueryBuilder buildQuery(Person person) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        if(!StringUtils.isEmpty(person.getId())){
            queryBuilder.must(QueryBuilders.matchQuery("id", person.getId()));
        }
        if(!StringUtils.isEmpty(person.getUserName())){
            queryBuilder.must(QueryBuilders.matchQuery("userName", person.getUserName()));
        }
        return queryBuilder;
    }
}
