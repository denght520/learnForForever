package com.learn.forever.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author : huangziwang
 * @version 1.0
 * @projectName：category-common
 * @className：ReflectionUtils
 * @date 2019年4月20日 下午5:11:25
 * @E-mail:huangziwang@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return 反射工具类
 */
public class ReflectionUtils {
    private static Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);


    /**
     * @param object
     * @param clazz
     * @return
     */
    public static <T> T convert(Object object, Class<T> clazz) {
        if (object == null || clazz == null) {
            return null;
        }
        Object obj = null;
        ParserConfig.getGlobalInstance().setAsmEnable(false);
        String text = JSON.toJSONString(object);
        obj = JSON.parseObject(text, clazz);

        return (T) obj;
    }

    public static <T> List<T> convertList(List<?> list, Class<T> targetClass) {
        if (list == null || list.size() < 1) {
            return new ArrayList();
        } else {
            List<T> listDest = new ArrayList();
            Iterator var3 = list.iterator();
            while (var3.hasNext()) {
                Object source = var3.next();
                T target = convert(source, targetClass);
                listDest.add(target);
            }
            return listDest;
        }
    }

    public static <T> PageInfo<T> convertPage(PageInfo<?> page, Class<T> targetClass) {
        if (page == null) {
            return null;
        }
        PageInfo<T> convert = ReflectionUtils.convert(page, PageInfo.class);
        List<?> list = page.getList();
        List<T> targetList = convertList(list, targetClass);
        convert.setList(targetList);
        return convert;
    }

    /**
     * 循环向上转型, 获取对象的 DeclaredMethod
     *
     * @param object         : 子类对象
     * @param methodName     : 父类中的方法名
     * @param parameterTypes : 父类中的方法参数类型
     * @return 父类中的方法对象
     */

    public static Method getDeclaredMethod(Object object, String methodName,
                                           Class<?>... parameterTypes) {
        Method method = null;

        for (Class<?> clazz = object.getClass(); clazz != Object.class; clazz = clazz
                .getSuperclass()) {
            try {
                method = clazz.getDeclaredMethod(methodName, parameterTypes);
                return method;
            } catch (Exception e) {
                // 这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                // 如果这里的异常打印或者往外抛，则就不会执行clazz =
                // clazz.getSuperclass(),最后就不会进入到父类中了
            }
        }

        return null;
    }

    /**
     * 直接调用对象方法, 而忽略修饰符(private, protected, default)
     *
     * @param object         : 子类对象
     * @param methodName     : 父类中的方法名
     * @param parameterTypes : 父类中的方法参数类型
     * @param parameters     : 父类中的方法参数
     * @return 父类中方法的执行结果
     */

    public static Object invokeMethod(Object object, String methodName,
                                      Class<?>[] parameterTypes, Object[] parameters) {
        /* 根据 对象、方法名和对应的方法参数 通过反射 调用上面的方法获取 Method 对象 */
        Method method = getDeclaredMethod(object, methodName, parameterTypes);

        /* 抑制Java对方法进行检查,主要是针对私有方法而言 */
        method.setAccessible(true);

        try {
            if (null != method) {
                return method.invoke(object, parameters); // 调用object 的 method
                // 所代表的方法，其方法的参数是
                // parameters
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
