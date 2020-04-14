package com.learn.forever.core.util;

import java.math.BigDecimal;

/**
 * @className: RnbUtils
 * @description: 人民币工具类
 * @author: Allen
 * @date: 2019/6/13 20:24
 * @version: 1.0
 * @E-mail: jiangyehui@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 */
public class RmbUtils {

    public static final BigDecimal HUNDRED = BigDecimal.valueOf(100);


    /**
     * @description 元转分
     * @date 2019/6/13 20:29
     * @param
     * @return
     */
    public static Long yuanConvertFen(BigDecimal amount){
        amount = null == amount ? BigDecimal.ZERO : amount;
        return amount.multiply(HUNDRED).longValue();
    }

    /**
     * @description 分转元
     * @date 2019/6/13 20:34
     * @param
     * @return
     */
    public static BigDecimal fenConvertYuan(Long amount){
        amount = null == amount ? 0L : amount;
        return BigDecimal.valueOf(amount).divide(HUNDRED, 2, BigDecimal.ROUND_DOWN);
    }

    /**
     * @description 分转元
     * @date 2019/6/13 20:35
     * @param
     * @return
     */
    public static BigDecimal fenConvertYuan(BigDecimal amount){
        amount = null == amount ? BigDecimal.ZERO : amount;
        return amount.divide(HUNDRED, 2, BigDecimal.ROUND_DOWN);
    }


}
