package com.learn.forever.core.annotation;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * @projectName: learnForForever
 * @className: ParamCheckConfg
 * @author: denghaitao
 * @date: 2020/1/15
 * @version: 1.0
 * @E-mail: denghaitao@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
@Aspect
@Component
@Slf4j
public class ParamCheckConfg {

//    @Before("@annotation(ParamCheck)")
//    public void before(JoinPoint joinPoint) throws IllegalAccessException {
//        System.out.println("进入注解");
//        Object[] args = joinPoint.getArgs();
//        String name = joinPoint.getSignature().getName();
//        for (Object o : args){
//            Field[] declaredFields = o.getClass().getDeclaredFields();
//            for (Field field : declaredFields){
//                field.setAccessible(true);
//                if(field.isAnnotationPresent(NotNull.class)){
//                    Object o1 = field.get(o);
//                    if(StringUtils.isEmpty(o1)){
//                        throw new RuntimeException(name+"方法的参数"+field.getName()+"不能为空");
//                    }
//                }
//            }
//        }
//    }

    @Around("@annotation(ParamCheck)")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        String name = proceedingJoinPoint.getTarget().getClass().getSimpleName();
        String metherName = proceedingJoinPoint.getSignature().getName();
        for (Object arg : args){
            log.info("[{}的方法= {}的参数为={}]", name, metherName, JSON.toJSONString(args));
            Field[] declaredFields = arg.getClass().getDeclaredFields();
            for(Field field : declaredFields){
                field.setAccessible(true);
                if(field.isAnnotationPresent(NotNull.class)){
                    Object o = field.get(arg);
                    if(StringUtils.isEmpty(o)){
                        throw new RuntimeException(name+"的"+metherName+"方法的参数"+field.getName()+"不能为空");
                    }
                }
            }
        }
        Object proceed = proceedingJoinPoint.proceed();
        log.info("[{} {}方法的执行结果为{}]", name, metherName, proceed);
    }

}
