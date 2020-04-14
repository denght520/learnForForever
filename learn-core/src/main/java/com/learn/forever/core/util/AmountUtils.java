package com.learn.forever.core.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * @author : huangziwang
 * @version 1.0
 * @projectName：item-common
 * @className：AmountUtils
 * @date 2019年5月7日 下午5:01:20
 * @E-mail:huangziwang@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return 金额元分之间转换工具类
 */
public class AmountUtils {

    /**
     * 金额为分的格式
     */
    public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";

    /**
     * 将分为单位的转换为元并返回金额格式的字符串 （除100）
     *
     * @param amount
     * @return
     * @throws Exception
     */
    public static String changeF2Y(Long amount) {
        if (amount == null) {
            return "0.00";
        }
//        if (amount.toString().matches(CURRENCY_FEN_REGEX)) {
//            throw new RuntimeException("金额格式有误");
//        }

        int flag = 0;
        String amString = amount.toString();
        if (amString.charAt(0) == '-') {
            flag = 1;
            amString = amString.substring(1);
        }
        StringBuffer result = new StringBuffer();
        if (amString.length() == 1) {
            result.append("0.0").append(amString);
        } else if (amString.length() == 2) {
            result.append("0.").append(amString);
        } else {
            String intString = amString.substring(0, amString.length() - 2);
            for (int i = 1; i <= intString.length(); i++) {
                if ((i - 1) % 3 == 0 && i != 1) {
                    // result.append(",");
                }
                result.append(intString.substring(intString.length() - i, intString.length() - i + 1));
            }
            result.reverse().append(".").append(amString.substring(amString.length() - 2));
        }
        if (flag == 1) {
            return "-" + result.toString();
        } else {
            return result.toString();
        }
    }

    /**
     * 将分为单位的转换为元 （除100）
     *
     * @param amount
     * @return
     * @throws Exception
     */
    public static Long changeF2Y(String amount) {
//		if (!amount.matches(CURRENCY_FEN_REGEX)) {
//			throw new RuntimeException("金额格式有误");
//		}
        return BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100)).longValue();
    }

    /**
     * desc long类型的分转换为元类型的bigDecimal author liqingzhen time 2019/6/15 16:17 Mail
     * liqingzhen@oxyzgroup.com
     *
     * @param amount
     * @return
     */
    public static BigDecimal changeLongCent2BigDeciaml(Long amount) {
        if (amount == null) {
            return null;
        }
        return new BigDecimal(amount).divide(new BigDecimal(100));
    }

    /**
     * 将元为单位的转换为分 （乘100）
     *
     * @param amount
     * @return
     */
    public static Long changeY2F(Long amount) {
        return BigDecimal.valueOf(amount).multiply(new BigDecimal(100)).longValue();
    }

    /**
     * 将元为单位的转换为分 替换小数点，支持以逗号区分的金额
     *
     * @param amount
     * @return
     */
    public static Long changeY2F(String amount) {
        String currency = amount.replaceAll("\\$|\\￥|\\,", ""); // 处理包含, ￥
        // 或者$的金额
        int index = currency.indexOf(".");
        int length = currency.length();
        Long amLong = 0l;
        if (index == -1) {
            amLong = Long.valueOf(currency + "00");
        } else if (length - index >= 3) {
            amLong = Long.valueOf((currency.substring(0, index + 3)).replace(".", ""));
        } else if (length - index == 2) {
            amLong = Long.valueOf((currency.substring(0, index + 2)).replace(".", "") + 0);
        } else {
            amLong = Long.valueOf((currency.substring(0, index + 1)).replace(".", "") + "00");
        }
        return amLong;
    }

    /**
     * @param price
     * @return
     * @description:过滤掉尾部0
     * @author: huangziwang
     * @version: v0.1
     * @time: 2019年6月24日 下午1:59:59
     */
    public static String replaceZero(String price) {
        if (StringUtils.isNotBlank(price)) {
            if (price.indexOf(".") > 0) {
                // 正则表达
                price = price.replaceAll("0+?$", "");// 去掉后面无用的零
                price = price.replaceAll("[.]$", "");// 如小数点后面全是零则去掉小数点
            }
        }
        return price;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(AmountUtils.changeF2Y(1000000000L));
        System.out.println(AmountUtils.changeF2Y("133"));
        System.out.println(AmountUtils.changeY2F("1.33"));
        System.out.println(AmountUtils.checkMoney(-3L));
        try {
            System.out.println(AmountUtils.changeF2Y("404.00"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(AmountUtils.stripZeroString(1000L));
        System.out.println(AmountUtils.stripZeroString(1020L));
    }

    /**
     * 金额不少于0处理
     */

    public static Long checkMoney(Long m) {
        Long n = 0L;
        if (m.longValue() < 1) {
            return n;
        }
        return m;
    }

    /**
     * Long类型的金额转string并去0
     */
    public static String stripZeroString(Long amount) {
        if (amount == null || amount.compareTo(0L) <= 0) {
            return "0";
        }
        BigDecimal decimal = new BigDecimal(amount);
        BigDecimal realAmount = decimal.divide(new BigDecimal(100));
        return realAmount.stripTrailingZeros().toPlainString();
    }

}
