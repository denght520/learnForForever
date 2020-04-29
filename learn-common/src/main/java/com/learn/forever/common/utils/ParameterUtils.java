package com.learn.forever.common.utils;

import com.learn.forever.common.enums.BaseExceptionEnum;
import com.learn.forever.common.enums.IExceptionEnum;
import com.learn.forever.common.enums.LearnExceptionEnum;
import com.learn.forever.common.exception.LearnException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author : Guava
 * @version 1.0
 * @projectName：bw-union
 * @className：ParamerUtils
 * @date 2019-12-20 20:15
 * @E-mail:gongdexing@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
public class ParameterUtils {

    public static void checkEmpty(Number number, String errorMsg) throws LearnException {
        if (number == null) {
            throw new LearnException(BaseExceptionEnum.BW_SYS_1000003, errorMsg);
        }
    }

    public static void checkEmpty(Number number) throws LearnException {
        if (number == null) {
            throw new LearnException(BaseExceptionEnum.BW_SYS_1000003);
        }
    }

    public static void checkNull(Object object) throws LearnException {
        if (object == null) {
            throw new LearnException(BaseExceptionEnum.BW_SYS_1000003);
        }
    }

    public static void checkNull(Object object, String msg) throws LearnException {
        if (object == null) {
            throw new LearnException(BaseExceptionEnum.BW_SYS_1000003, msg);
        }
    }


    public static void checkEmpty(String parameter) throws LearnException {
        if (StringUtils.isAllBlank(parameter)) {
            throw new LearnException(BaseExceptionEnum.BW_SYS_1000003);
        }
    }

    public static void checkEmpty(String parameter, String errorMsg) throws LearnException {
        if (StringUtils.isAllBlank(parameter)) {
            throw new LearnException(BaseExceptionEnum.BW_SYS_1000003, errorMsg);
        }
    }

    public static void checkEmpty(List<?> list) throws LearnException {
        if (CollectionUtils.isEmpty(list)) {
            throw new LearnException(BaseExceptionEnum.BW_SYS_1000003);
        }
    }

    public static void check(boolean b, IExceptionEnum iExceptionEnum) throws LearnException {
        if (b) {
            throw new LearnException(iExceptionEnum);
        }
    }

    public static void check(boolean b, IExceptionEnum iExceptionEnum, String msg) throws LearnException {
        if (b) {
            throw new LearnException(LearnExceptionEnum.BW_SALESPLUG_990017, msg);
        }
    }

    /**
     * @param id
     * @return
     * @throws
     * @description:验证id是否有效
     * @author: huangziwang
     * @version: v0.1
     * @time: 2019年6月29日 下午2:12:02
     */
    public static boolean checkIdIsNull(Long id, String msg) throws LearnException {
        if (id == null || id <= 0) {
            throw new LearnException(BaseExceptionEnum.BW_SYS_1000003, msg);
        }
        return false;
    }

    /**
     * @param id
     * @return
     * @throws
     * @description:验证id是否有效
     * @author: huangziwang
     * @version: v0.1
     * @time: 2019年6月29日 下午2:12:02
     */
    public static boolean checkIdIsNull(Integer id, String msg) throws LearnException {
        if (id == null || id <= 0) {
            throw new LearnException(BaseExceptionEnum.BW_SYS_1000003, msg);
        }
        return false;
    }

    public static void checkEmpty(Object param, String error) throws LearnException {
        if (org.springframework.util.StringUtils.isEmpty(param)) {
            throw new LearnException(LearnExceptionEnum.BW_SALESPLUG_990004, error);
        }
    }

    public static void checkEmpty(List<?> list, String msg) throws LearnException {
        if (CollectionUtils.isEmpty(list)) {
            throw new LearnException(BaseExceptionEnum.BW_SYS_1000003, msg);
        }
    }
}
