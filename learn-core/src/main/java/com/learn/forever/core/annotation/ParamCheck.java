package com.learn.forever.core.annotation;

import java.lang.annotation.*;

/**
 * @projectName: learnForForever
 * @className: ParamCheck
 * @author: denghaitao
 * @date: 2020/1/15
 * @version: 1.0
 * @E-mail: denghaitao@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface ParamCheck {
}
