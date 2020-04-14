package com.learn.forever.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : yangwangsen
 * @version 1.0
 * @projectName：bw-common
 * @className：ChineseAndEnglishUtil
 * @date 2019/5/23 11:28
 * @E-mail:yangwangsen@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
public class ChineseAndEnglishUtil {

    /**
     * 是否是中文
     *
     * @param c
     * @return
     */

    public static boolean isChinese(char c) {

        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);

        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS

                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS

                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A

                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION

                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION

                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {

            return true;

        }

        return false;

    }

    /**
     * 是否是英文
     *
     * @param
     * @return
     */

    public static boolean isEnglish(String charaString) {

        return charaString.matches("^[a-zA-Z]*");

    }

    public static boolean isChinese(String str) {

        String regEx = "[\\u4e00-\\u9fa5]+";

        Pattern p = Pattern.compile(regEx);

        Matcher m = p.matcher(str);

        if (m.find())

            return true;

        else

            return false;

    }


    public static void main(String[] args) {

        System.out.println(isChinese('员'));

        System.out.println(isChinese('s'));

        System.out.println(isEnglish("程序员之家"));

        System.out.println(isEnglish("robert"));

        System.out.println(isChinese("程序员论坛"));

        System.out.println("saddasd".toUpperCase());

    }
}
