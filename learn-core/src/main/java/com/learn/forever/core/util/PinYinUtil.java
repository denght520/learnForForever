package com.learn.forever.core.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : yangwangsen
 * @version 1.0
 * @projectName：category
 * @className：PinYinUtil
 * @date 2019/5/7 21:03
 * @E-mail:yangwangsen@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
public class PinYinUtil {


    /**
     * 正则去掉所有字符操作
     *
     * @param chines
     * @return
     */
    public static String cleanChar(String chines) {
        chines = chines.replaceAll("[\\p{Punct}\\p{Space}]+", "");
        // 正则表达式去掉所有中文的特殊符号
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}<>《》【】‘；：”“’。，、？]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(chines);
        chines = matcher.replaceAll("").trim();
        return chines;
    }

    /**
     * 获取汉字全拼小写
     *
     * @param chines
     * @return
     */
    public static String converterToAllSpell(String chines) {
        chines = cleanChar(chines);
        String pinyinName = "";
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > 128) {
                try {
                    pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0];
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    //e.printStackTrace();
                } catch (ArrayIndexOutOfBoundsException e) {

                }
            } else {
                pinyinName += nameChar[i];
            }
        }
        return pinyinName;

    }

    /**
     * 中文首字母
     *
     * @param chines
     * @return
     */
    public static String converterToFirstSpell(String chines) {
        chines = cleanChar(chines);
        String pinyinName = "";
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > 128) {
                try {
                    String[] strings = PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat);
                    pinyinName += strings[0].substring(0, 1);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    //e.printStackTrace();
                } catch (ArrayIndexOutOfBoundsException e) {

                }
            } else {
                pinyinName += nameChar[i];
            }
        }
        return pinyinName;
    }

    public static void main(String[] args) {
        String s = "中国";
        System.out.println(converterToAllSpell(s));
        System.out.println(converterToFirstSpell(s));
    }


}
