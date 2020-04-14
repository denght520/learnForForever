package com.learn.forever.core.util;

import java.math.BigDecimal;

/**
* @projectName：bw-common
* @className：BigDecimalUtil
* @author : huangziwang
* @date 2019年5月18日 下午4:41:48
* @version 1.0
* @E-mail:huangziwang@oxyzgroup.com
* @Copyright: 版权所有 (C) 2019 蓝鲸淘.
* @return 金钱比较工具类
*/
public class BigDecimalCompareUtil {


	/** 数字1大于数字2
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static boolean value1DayuValue2 (BigDecimal value1 , BigDecimal value2) {
		if (value1 != null && value2 != null) {
			if (value1.compareTo(value2) == 1) {
				return true;
			}
		}
		return false;
	}

	/** 数字1等于数字2
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static boolean value1DengyuValue2 (BigDecimal value1 , BigDecimal value2) {
		if (value1 != null && value2 != null) {
			if (value1.compareTo(value2) == 0) {
				return true;
			}
		}
		return false;
	}

	/** 数字1小于数字2
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static boolean value1XiaoyuValue2 (BigDecimal value1 , BigDecimal value2) {
		if (value1 != null && value2 != null) {
			if (value1.compareTo(value2) == -1) {
				return true;
			}
		}
		return false;
	}

	/** 数字1大于等于数字2
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static boolean value1DayuDengyuValue2 (BigDecimal value1 , BigDecimal value2) {
		return value1DayuValue2(value1, value2) || value1DengyuValue2(value1, value2) ;
	}

	/** 数字1小于等于等于数字2
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static boolean value1XiaoyuDengyuValue2 (BigDecimal value1 , BigDecimal value2) {
		return value1XiaoyuValue2(value1, value2) || value1DengyuValue2(value1, value2) ;
	}

	public static void main(String[] args) {
		System.out.println(value1DayuValue2( new BigDecimal("1"),  new BigDecimal("0")));
		System.out.println(value1DengyuValue2( new BigDecimal("1"),  new BigDecimal("1.00")));
		System.out.println(value1XiaoyuValue2( new BigDecimal("1"),  new BigDecimal("2")));
		System.out.println(value1DayuDengyuValue2( new BigDecimal("1"),  new BigDecimal("1")));
		System.out.println(value1DayuDengyuValue2( new BigDecimal("2"),  new BigDecimal("1")));
		System.out.println(value1XiaoyuDengyuValue2( new BigDecimal("1"),  new BigDecimal("2")));
		System.out.println(value1XiaoyuDengyuValue2( new BigDecimal("1"),  new BigDecimal("1")));


		double   f   =   111231.444;
		BigDecimal   b   =   new   BigDecimal(f);
		//保留2位小数
		BigDecimal   f1   =   b.setScale(0,   BigDecimal.ROUND_DOWN);

		System.out.println(f1);
	}
}
