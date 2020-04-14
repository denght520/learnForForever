package com.learn.forever.core.util;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Allen
 * @version 1.0
 * @className: ConvertUtil
 * @description: 类型转化工具类
 * @date 2019/6/26 16:25
 * @E-mail: jiangyehui@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 */
@Deprecated
@Slf4j
public class ConvertUtil {

    public static Object convert(Object target, Object... sources) {
        for (Object element : sources) {
            target = convert(element, target);
        }
        return target;
    }

    public static Object convert(Object source, Object target) {
        if (null == target) {
            return null;
        }
        if (null == source) {
            return target;
        }

        BeanUtils.copyProperties(source, target);
        return target;
    }

    public static <T> T convert(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        try {
            T target = targetClass.newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("convert obj error", e);
            return null;
        }
    }

    public static <T> List<T> convert(List source, Class<T> clazz) {
        ArrayList<T> result = Lists.newArrayList();
        if (CollectionUtils.isEmpty(source)) {
            return result;
        }
        for (Object s : source) {
            result.add(convert(s, clazz));
        }
        return result;
    }


    /**
     * 把分页对象转换为指定的分页对象
     *
     * @param resource PageInfo 对象
     * @param target   目标对象
     * @return PageInfo<T>
     */
    public static <T> PageInfo<T> convert(PageInfo resource, Class<T> target) {
        if (null == resource) {
            return resource;
        }
        resource.setList(ConvertUtil.convert(resource.getList(), target));
        return resource;
    }
}
