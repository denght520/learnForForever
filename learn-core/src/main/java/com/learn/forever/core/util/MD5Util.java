package com.learn.forever.core.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author : huangziwang
 * @version 1.0
 * @projectName：bw-common
 * @className：MD5Util
 * @date 2019年4月27日 上午10:00:15
 * @E-mail:huangziwang@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return md5加密工具类
 */
public class MD5Util {

    public static void main(String[] args) {
        System.out.println(MD5Util.md5("ASD123456"));
        System.out.println(MD5Util.MD5("123456"));

        System.out.println("1e55dbf412cb74d5e2c21fb6452408c7".equals(MD5Util.md5("asd123456")));
    }

    /**
     * @param s
     * @return
     * @description:md5大写加密
     * @author: huangziwang
     * @version: v0.1
     * @time: 2019年4月27日 上午9:59:52
     */
    public static String MD5(String s) {
        return md5(s).toUpperCase();
    }

    /**
     * @param s
     * @return
     * @description:md5小写加密
     * @author: huangziwang
     * @version: v0.1
     * @time: 2019年4月27日 上午10:00:00
     */
    public static String md5(String s) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(s.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] byteArray = messageDigest.digest();
        StringBuilder md5StrBuff = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(
                        Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return md5StrBuff.toString();
    }

}
