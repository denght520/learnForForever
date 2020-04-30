package com.learn.forever.damin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : zhengrui
 * @version 1.0
 * @projectName：searchrt
 * @className：EsOperateDO
 * @date 2019-12-09 10:53
 * @E-mail:zhengrui@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EsOperateDO {

    /**
     * 索引名称
     */
    private String indexName;

    /**
     * 索引类型
     */
    private String indexType;

    /**
     * 索引数据
     */
    private Object data;

    /**
     * 文档id
     */
    private Long id;

    /**
     * 更新如果不存在要不要插入
     */
    private boolean upsert;
}
