package com.learn.forever.core.util;

import java.util.Random;

/**
* @projectName：bw-common
* @className：RandomUtil
* @author : huangziwang
* @date 2019年5月1日 上午10:16:47
* @version 1.0
* @E-mail:huangziwang@oxyzgroup.com
* @Copyright: 版权所有 (C) 2019 蓝鲸淘.
* @return 随机工具类
*/
public class RandomUtil {

    private static Random random = new Random();

     /**
     * @description:获取随机字符串
     * @author: huangziwang
     * @version:  v0.1
     * @time: 2019年5月1日 上午10:16:36
     *  @param count
     *  @return
     */
    public static String getRandomString(int count) {
        StringBuilder randomNumber = new StringBuilder();
        for (int i = 0; i < count; i++) {
            Random ran = new Random();
            int k = ran.nextInt(62);
            if (k < 10) {
                int random = ran.nextInt(10);
                String temp = String.valueOf(random);
                randomNumber.append(temp);
            } else if (k < 36) {
                int random = 97 + ran.nextInt(26);
                String temp = String.valueOf((char) (byte) random);
                randomNumber.append(temp);
            } else {
                int random = 65 + ran.nextInt(26);
                String temp = String.valueOf((char) (byte) random);
                randomNumber.append(temp);
            }
        }
        return randomNumber.toString();
    }

    /**
     * @description:获取随机数字
     * @author: huangziwang
     * @version: v0.1
     * @time: 2019年5月1日 上午10:16:27
     * @param count
     * @return
     */
    public static String getRandomNumber(int count) {
        String ran = "";
        for (int i = 0; i < count; i++) {
            ran += random.nextInt(9);
        }
        return ran;
    }

    public static void main(String[] args) {
        System.out.println(getRandomNumber(11));
        System.out.println(getRandomString(10));
    }
}
