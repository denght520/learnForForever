package com.learn.forever.core.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * @author : huangziwang
 * @version 1.0
 * @projectName：category-common
 * @className：StringUtil
 * @date 2019年4月20日 下午5:11:16
 * @E-mail:huangziwang@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return 字符串工具类
 */
public class StringUtil {

    public static final Pattern pattern = Pattern.compile("[(*)+]");

    /**
     * 转义换行\n,\\n前端解析
     *
     * @param str
     * @return
     */
    public static String escapeLine(String str) {
        if (str == null) {
            return null;
        }
        String json = JSON.toJSONString(str);
        return json.substring(1, json.length() - 1);
    }

    /**
     * @param str
     * @return
     * @description:防止sql注入
     * @author: huangziwang
     * @version: v0.1
     * @time: 2019年4月24日 上午9:45:03
     */
    public static String TransactSQLInjection(String str) {
        return str.replaceAll(".*([';]+|(--)+).*", " ");

    }

    /**
     * 字符串列表转换为以|隔开的字符串
     *
     * @param list
     * @return
     */
    public static String convertListToStringSplitVertical(List<String> list) {
        StringBuffer result = new StringBuffer();
        for (String temp : list) {
            if (result.length() > 0) {
                result.append("|");
            }
            result.append(temp);
        }
        return result.toString();
    }

    /**
     * @param str   字符串
     * @param begin 哪里开始打
     * @param end   打到哪里
     * @return
     * @description 打*
     * @author: huangziang
     * @version: v0.1
     * @time: 2019年4月23日 下午2:17:21
     */
    public static String getMask(String str, int begin, int end) {

        if (StringUtils.isNotBlank(str) && end - begin >= 0) {

            int length = str.length();

            int count = end - begin;

            if (count == 0) {// 截取为0，默认1到末尾
                count = 1;
                end = length;
            }

            if (count >= 6) {// 最多打6位*
                count = 6;
            }

            if (length > begin && length >= end && length >= count) {
                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < count; i++) {
                    sb.append("*");
                }

                String beginStr = str.substring(0, begin);
                String endStr = str.substring(end);

                return beginStr + sb.toString() + endStr;
            }

        }

        return str;
    }

    /**
     * @param str
     * @return
     * @description:字符串转为整型数组列表
     * @author: huangziwang
     * @version: v0.1
     * @time: 2019年4月24日 上午9:46:27
     */
    public static List<Integer> stringtoIntList(String str) {
        List<Integer> list = new ArrayList<Integer>();
        if (StringUtils.isNotBlank(str)) {
            StringTokenizer toKenizer = new StringTokenizer(str, ",");
            while (toKenizer.hasMoreElements()) {
                Integer i = Integer.valueOf(toKenizer.nextToken());
                list.add(i);

            }
        }
        return list;
    }

    /**
     * @param str
     * @return
     * @description:字符串转为long数组列表
     * @author: huangziwang
     * @version: v0.1
     * @time: 2019年4月24日 上午9:46:27
     */
    public static List<Long> stringtoLongList(String str) {
        List<Long> list = new ArrayList<Long>();
        if (StringUtils.isNotBlank(str)) {
            StringTokenizer toKenizer = new StringTokenizer(str, ",");
            while (toKenizer.hasMoreElements()) {
                Long i = Long.valueOf(toKenizer.nextToken());
                list.add(i);
            }
        }
        return list;
    }

    /**
     * @param str
     * @return
     * @description:字符串转为字符串列表
     * @author: huangziwang
     * @version: v0.1
     * @time: 2019年4月24日 上午9:46:35
     */
    public static List<String> stringtoStringList(String str) {
        List<String> list = new ArrayList<String>();
        if (StringUtils.isNotBlank(str)) {
            StringTokenizer toKenizer = new StringTokenizer(str, ",");
            while (toKenizer.hasMoreElements()) {
                String i = toKenizer.nextToken();
                list.add(i);

            }
        }
        return list;
    }

    /**
     * @param list
     * @return
     * @description:整型数组列表转为字符串
     * @author: huangziwang
     * @version: v0.1
     * @time: 2019年4月24日 上午9:46:44
     */
    public static String intListToString(List<Integer> list) {
        if (list == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        for (Integer temp : list) {
            if (flag) {
                result.append(",");
            } else {
                flag = true;
            }
            result.append(temp);
        }
        return result.toString();
    }

    /**
     * @param list
     * @return
     * @description:long组列表转为字符串
     * @author: huangziwang
     * @version: v0.1
     * @time: 2019年4月24日 上午9:46:44
     */
    public static String longListToString(List<Long> list) {
        if (list == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        for (Long temp : list) {
            if (flag) {
                result.append(",");
            } else {
                flag = true;
            }
            result.append(temp);
        }
        return result.toString();
    }

    /**
     * @param strings
     * @return
     * @description:字符串数组列表转为字符串
     * @author: huangziwang
     * @version: v0.1
     * @time: 2019年4月24日 上午9:46:53
     */
    public static String intStringArrayToString(String[] strings) {
        if (strings == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        for (String temp : strings) {
            if (flag) {
                result.append(",");
            } else {
                flag = true;
            }
            result.append(temp);
        }
        return result.toString();
    }

    /**
     * @param strings
     * @return
     * @description:字符串列表转为逗号隔开的字符串
     * @author: chenenling
     * @version: v0.1
     * @time: 2018年6月11日 下午5:18:24
     */
    public static String intListArrayToString(List<String> strings) {
        if (strings == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        for (String temp : strings) {
            if (flag) {
                result.append(",");
            } else {
                flag = true;
            }
            result.append(temp);
        }
        return result.toString();
    }

    public static String getReLabelContent(String content) {
        if (StringUtils.isBlank(content)) {
            return "";
        }
        return pattern.matcher(content).replaceAll("");
    }

    public static String getReEnterContent(String content) {
        if (StringUtils.isBlank(content)) {
            return "";
        }
        return content.replaceAll("\n", "").replaceAll("\r\n", "");
    }

    public static String getReLabelAndEnterContent(String content) {
        if (StringUtils.isBlank(content)) {
            return "";
        }
        return getReEnterContent(getReLabelContent(content));
    }
}
