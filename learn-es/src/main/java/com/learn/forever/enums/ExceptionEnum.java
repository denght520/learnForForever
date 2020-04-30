package com.learn.forever.enums;

import com.bluewhale.base.enums.IExceptionEnum;

/**
 * @author : zhengrui
 * @version 1.0
 * @projectName：refund
 * @className：ExceptionEnum
 * @date 2019-05-18 18:55
 * @E-mail:zhengrui@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
public enum ExceptionEnum implements IExceptionEnum {

    BW_SEARCH_6000001(6000001, "ES查询异常"),
    BW_SEARCH_6000002(6000002, "ES更新异常"),
    BW_SEARCH_6000003(6000003, "ES插入异常"),
    BW_SEARCH_6000022(6000022, "调用公共服务返回失败"),
    BW_SEARCH_6000023(6000023, "调用公共服务返回为空"),
    BW_SEARCH_6000024(6000024, "调用商品服务返回失败"),
    BW_SEARCH_6000025(6000025, "调用商品返回为空"),
    BW_SEARCH_6000026(6000025, "更新分数评论为空"),
    ;

    private Integer code;
    private String message;

    ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
