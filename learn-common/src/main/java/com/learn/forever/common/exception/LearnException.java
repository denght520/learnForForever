package com.learn.forever.common.exception;


import com.learn.forever.common.enums.IExceptionEnum;

/**
 * @author : Guava
 * @version 1.0
 * @projectName：bw-sales-plug
 * @className：SalesPlugException
 * @date 2020/1/29 12:34 下午
 * @E-mail:gongdexing@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2020 蓝鲸淘.
 * @return
 */
public class LearnException extends BaseException {


    public LearnException(IExceptionEnum exceptionEnum, String userErrorMsg) {
        super(exceptionEnum, userErrorMsg);
    }

    public LearnException(IExceptionEnum exceptionEnum, String userErrorMsg, Throwable cause) {
        super(exceptionEnum, userErrorMsg, cause);
    }

    public LearnException(IExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }


}
