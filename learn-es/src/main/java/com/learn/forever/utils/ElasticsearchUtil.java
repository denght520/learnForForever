package com.learn.forever.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bluewhale.query.domain.SortQuery;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.learn.forever.Constants;
import com.learn.forever.damin.EsOperateDO;
import com.learn.forever.enums.ExceptionEnum;
import com.learn.forever.expection.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author chy
 * @Title: ElasticsearchUtil
 * @Description: 工具类
 * @date 2018/4/24 15:40
 */
@Component
@Slf4j
public class ElasticsearchUtil {

    @Resource(name = "restHighLevelClient")
    private RestHighLevelClient restHighLevelClient;

    private static RestHighLevelClient client;

    public static EsOperateDO getOperateDO(String indexName, String indexType, Long id, Object data) {
        EsOperateDO esOperateDO = new EsOperateDO();
        esOperateDO.setData(data);
        esOperateDO.setIndexName(indexName);
        esOperateDO.setIndexType(indexType);
        esOperateDO.setId(id);
        return esOperateDO;
    }

    public static SearchResponse search(QueryBuilder queryBuilder, String index) {
        try {
            SearchRequest searchRequest = new SearchRequest(index);
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(queryBuilder);
            log.info("request : {}", sourceBuilder.toString());
            searchRequest.source(sourceBuilder);
            return client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("search exception:", e);
            throw new BizException(ExceptionEnum.BW_SEARCH_6000001);
        }
    }

    /**
     * 根据布尔条件进行查询
     *
     * @param queryBuilder
     * @return
     */
    public static <T> T searchOne(QueryBuilder queryBuilder, Class<T> clazz, String index) {
        try {
            SearchRequest searchRequest = new SearchRequest(index);
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(queryBuilder);
            log.info("request : {}", sourceBuilder.toString());
            searchRequest.source(sourceBuilder);
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            if (searchResponse != null) {
                SearchHits hits = searchResponse.getHits();
                if (hits != null && hits.totalHits > 0) {
                    Map<String, Object> sourceAsMap = hits.getAt(0).getSourceAsMap();
                    T data = JSONObject.parseObject(JSONObject.toJSONString(sourceAsMap), clazz);
                    return data;
                }
            }
        } catch (Exception e) {
            log.error("searchOne exception:", e);
            throw new BizException(ExceptionEnum.BW_SEARCH_6000001);
        }
        return null;
    }

    /**
     * 根据布尔条件进行查询
     *
     * @param queryBuilder
     * @return
     */
    public static <T> PageInfo<T> searchPage(QueryBuilder queryBuilder, String index, SortQuery sortQuery, Class<T> clazz, String defaultSort) {
        return searchPage(queryBuilder, index, sortQuery, null, clazz, defaultSort);
    }

    /**
     * @PostContruct是spring框架的注解 spring容器初始化的时候执行该方法
     */
    @PostConstruct
    public void init() {
        client = this.restHighLevelClient;
    }

    /**
     * 插入数据
     *
     * @return
     */
    public static String addData(EsOperateDO esOperateDO) {
        IndexRequest indexRequest = new IndexRequest(esOperateDO.getIndexName(), esOperateDO.getIndexType(), esOperateDO.getId().toString());
        try {
            Object data = esOperateDO.getData();
            indexRequest.source(JSONObject.toJSONStringWithDateFormat(data, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat), XContentType.JSON);
            IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
            return indexResponse.getId();
        } catch (Exception e) {
            log.error("add data error", e);
            throw new BizException(ExceptionEnum.BW_SEARCH_6000003);
        }
    }

    /**
     * 更新数据
     *
     * @return
     */
    public static String updateData(EsOperateDO esOperateDO) {

        UpdateRequest updateRequest = new UpdateRequest(esOperateDO.getIndexName(), esOperateDO.getIndexType(), esOperateDO.getId().toString());
        try {
            Object data = esOperateDO.getData();
            updateRequest.doc(JSONObject.toJSONStringWithDateFormat(data, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat), XContentType.JSON);
            if (esOperateDO.isUpsert()) {
                updateRequest.docAsUpsert(true);
            }
            UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
            return updateResponse.getId();
        } catch (Exception e) {
            log.error("update data error", e);
            throw new BizException(ExceptionEnum.BW_SEARCH_6000002);
        }
    }

    /**
     * 获取低水平客户端
     *
     * @return
     */
    public static RestClient getLowLevelClient() {
        return client.getLowLevelClient();
    }


    public static <T> PageInfo<T> searchPage(QueryBuilder queryBuilder, String index, SortQuery sortQuery, List<SortQuery> sortQueryList, Class<T> clazz, String defaultSort) {
        return searchPage(queryBuilder, index, sortQuery, sortQueryList, null, clazz, defaultSort);
    }

    /**
     * 根据布尔条件进行查询
     *
     * @param queryBuilder
     * @return
     */
    public static <T> PageInfo<T> searchPage(QueryBuilder queryBuilder, String index, SortQuery sortQuery, List<SortQuery> sortQueryList, SortBuilder sortBuilder, Class<T> clazz, String defaultSort) {
        try {
            PageInfo<T> pageInfo = new PageInfo<>();
            pageInfo.setPageNum(Constants.PAGE);
            pageInfo.setPageSize(Constants.PAGE_SIZE);

            int start = Constants.SEARCH_START;
            int offset = Constants.SEARCH_OFFSET;
            SortOrder sortOrder = SortOrder.DESC;
            String sort = defaultSort;

            if (sortQuery != null) {
                start = (sortQuery.getPageNum() - 1) * sortQuery.getPageSize();
                offset = sortQuery.getPageSize();
                pageInfo.setPageNum(sortQuery.getPageNum());
                pageInfo.setPageSize(sortQuery.getPageSize());
                if (StringUtils.isNotEmpty(sortQuery.getOrderByField())) {
                    sort = sortQuery.getOrderByField();
                }
                if (sortQuery.getBlDesc() != null
                        && !sortQuery.getBlDesc()) {
                    sortOrder = SortOrder.ASC;
                }
            }

            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            if (start < 0) {
                start = Constants.SEARCH_START;
            }
            sourceBuilder.from(start);
            sourceBuilder.size(offset);

            //添加sortBuilder  复杂排序
            if (sortBuilder!=null){
                sourceBuilder.sort(sortBuilder);
            }

            if (!CollectionUtils.isEmpty(sortQueryList)) {
                for (int i = 0; i < sortQueryList.size(); i++) {
                    SortQuery sortSearch = sortQueryList.get(i);
                    SortOrder sortOrderSearch = SortOrder.DESC;
                    if (sortSearch.getBlDesc() != null
                            && !sortSearch.getBlDesc()) {
                        sortOrderSearch = SortOrder.ASC;
                    }
                    sourceBuilder.sort(sortSearch.getOrderByField(), sortOrderSearch);
                }
            } else {
                if (StringUtils.isNotEmpty(sort)) {
                    sourceBuilder.sort(sort, sortOrder);
                }
            }

            if (StringUtils.isNotEmpty(sort) && !Constants.KEY_ORDER_NO.equals(sort)) {
                sourceBuilder.docValueField(sort, "epoch_millis");
            }
            sourceBuilder.query(queryBuilder);
            log.info("request : {}", sourceBuilder.toString());

            SearchRequest searchRequest = new SearchRequest(index);
            searchRequest.source(sourceBuilder);

            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            List<T> list = Lists.newArrayList();
            if (searchResponse != null) {
                SearchHits hits = searchResponse.getHits();
                if (hits != null && hits.totalHits > 0) {
                    pageInfo.setTotal(hits.totalHits);
                    for (int i = 0; i < hits.getHits().length; i++) {
                        Map<String, Object> sourceAsMap = hits.getAt(i).getSourceAsMap();
                        T data = JSONObject.parseObject(JSONObject.toJSONString(sourceAsMap), clazz);
                        list.add(data);
                    }
                }
            }
            Page<T> page = new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize());
            page.setTotal(pageInfo.getTotal());
            page.addAll(list);
            return page.toPageInfo();
        } catch (Exception e) {
            log.error("search exception:", e);
            throw new BizException(ExceptionEnum.BW_SEARCH_6000001);
        }
    }

    /**
     * 根据布尔条件进行查询
     *
     * @param queryBuilder
     * @return
     */
    public static long count(QueryBuilder queryBuilder, String index) {
        try {
            String[] indices = Strings.splitStringByCommaToArray(index);
            CountRequest countRequest = new CountRequest(indices);
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(queryBuilder);
            countRequest.source(sourceBuilder);
            log.info("request : {}", sourceBuilder.toString());
            CountResponse countResponse = client.count(countRequest, RequestOptions.DEFAULT);
//            log.info("response : {}", countResponse.toString());
            return countResponse.getCount();
        } catch (Exception e) {
            log.error("search exception:", e);
            throw new BizException(ExceptionEnum.BW_SEARCH_6000001);
        }
    }

    /**
     * 根据布尔条件进行查询
     *
     * @param queryBuilder
     * @return
     */
    public static <T> List<T> searchList(QueryBuilder queryBuilder, Class<T> clazz, String index, Long count) {
        try {
            SearchRequest searchRequest = new SearchRequest(index);
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            if (count != null) {
                sourceBuilder.size(count.intValue());
            }
            sourceBuilder.query(queryBuilder);
            log.info("request : {}", sourceBuilder.toString());
            searchRequest.source(sourceBuilder);
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            List<T> list = Lists.newArrayList();
            if (searchResponse != null) {
                SearchHits hits = searchResponse.getHits();
                if (hits != null && hits.totalHits > 0) {
                    for (int i = 0; i < hits.getHits().length; i++) {
                        Map<String, Object> sourceAsMap = hits.getAt(i).getSourceAsMap();
                        T data = JSONObject.parseObject(JSONObject.toJSONString(sourceAsMap), clazz);
                        list.add(data);
                    }
                }
            }
            return list;
        } catch (Exception e) {
            log.error("search exception:", e);
            throw new BizException(ExceptionEnum.BW_SEARCH_6000001);
        }
    }

}
