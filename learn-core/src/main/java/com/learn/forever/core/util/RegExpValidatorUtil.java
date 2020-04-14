package com.learn.forever.core.util;

import com.bluewhale.constant.RegExpConstatns;

import java.util.regex.Pattern;

/**
 * @author : neling chen
 * @version 1.0
 * @projectName : bw-common
 * @className : RegExpValidatorUtil
 * @date 2019/6/3 16:26
 * @E-mail: chenneling@oxyzgroup.com
 * @copyright : 版权所有 (C) 2019 蓝鲸淘.
 * @return 正则验证工具
 */
public class RegExpValidatorUtil {
    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(RegExpConstatns.MOBILE, mobile);
    }

    /**
     * 校验身份证
     *
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIdCard(String idCard) {
        return Pattern.matches(RegExpConstatns.ID_CARD, idCard);
    }
}
