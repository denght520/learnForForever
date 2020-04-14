package com.learn.forever.core.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
* @projectName：category-common
* @className：DateUtil
* @author : huangziwang
* @date 2019年4月20日 下午5:11:33
* @version 1.0
* @E-mail:huangziwang@oxyzgroup.com
* @Copyright: 版权所有 (C) 2019 蓝鲸淘.
* @return 日期工具类
*/
public class DateUtil {

	static Calendar calendar;
	public DateUtil() {
		super();
	}
	private static Logger _log = LoggerFactory.getLogger(ReflectionUtils.class);

	/**
	 * 取出一个指定长度大小的随机正整数.
	 *
	 * @param length
	 *            int 设定所取出随机数的长度。length小于11
	 * @return int 返回生成的随机数。
	 */
	public static int buildRandom(int length) {
		int num = 1;
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}
		for (int i = 0; i < length; i++) {
			num = num * 10;
		}
		return (int) ((random * num));
	}

	/**
	 * @param date
	 * @return String yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateTimeToStr() {
		return DateToStr(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * @param date
	 * @return String yyyy-MM-dd
	 */
	public static String DateToStr(Date date) {
		return DateToStr(date, "yyyy-MM-dd");
	}

	/**
	 * @param Date
	 *            date
	 * @return String yyyyMMdd
	 */
	public static String dateToStr(Date date) {
		return DateToStr(date, "yyyyMMdd");
	}


	/**
	 * @param date
	 * @return String yyyy-MM-dd HH:mm:ss.SSS
	 */
	public static String DateTimeToStrBySSS(Date date) {
		return DateToStr(date, "yyyy-MM-dd HH:mm:ss.SSS");
	}

	/**
	 * @param date
	 * @return String yyyyMMddHHmmss
	 */
	public static String dateTimeToStr(Date date) {
		return DateToStr(date, "yyyyMMddHHmmss");
	}

	/**
	 * @param date
	 * @return String HH:mm:ss
	 */
	public static String TimeToStr(Date date) {
		return DateToStr(date, "HH:mm:ss");
	}

	/**
	 * @param date
	 * @return String HHmmss
	 */
	public static String timeToStr(Date date) {
		return DateToStr(date, "HHmmss");
	}

	/**
	 * @param date
	 * @return String yyyy
	 */
	public static String YearToStr(Date date) {
		return DateToStr(date, "yyyy");
	}

	/**
	 * @param date
	 * @return int year
	 */
	public static int YearToInt(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	/**
	 * @param date
	 * @return String MM
	 */
	public static String MonthToStr(Date date) {
		return DateToStr(date, "MM");
	}

	/**
	 * @param date
	 * @return int month
	 */
	public static int MonthToInt(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * @param date
	 * @return String dd
	 */
	public static String DayToStr(Date date) {
		return DateToStr(date, "dd");
	}

	/**
	 * @param date
	 * @return int day
	 */
	public static int DayToInt(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DATE);
	}

	/**
	 * @param date
	 * @return String yyyy-MM
	 */
	public static String YearMonthToStr(Date date) {
		return DateToStr(date, "yyyy-MM");
	}

	/**
	 * @param date
	 * @return String yyyyMM
	 */
	public static String yearMonthToStr(Date date) {
		return DateToStr(date, "yyyyMM");
	}

	/**
	 * @param date
	 * @return String MM-dd
	 */
	public static String MonthDayToStr(Date date) {
		return DateToStr(date, "MM-dd");
	}

	/**
	 * @param date
	 * @return String MMdd
	 */
	public static String monthDayToStr(Date date) {
		return DateToStr(date, "MMdd");
	}

	/**
	 * @param date
	 * @return dd/MM/yyyy
	 */
	public static String DayMonthYearToStr(Date date) {
		return DateToStr(date, "dd/MM/yyyy");
	}

	/**
	 * @param date
	 * @return yyyy/MM/dd
	 */
	public static String YearMonthDayToStr(Date date) {
		return DateToStr(date, "yyyy/MM/dd");
	}

	// ***************************************************************************

	/**
	 * @param sDate
	 *            yyyyMMdd
	 * @return Date
	 */
	public static Date strToDate(String sDate) {
		return StrToDate(sDate, "yyyyMMdd");
	}

	/**
	 * @param sDateTime
	 *            yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date SDateTimeToDate(String sDateTime) {
		return StrToDate(sDateTime, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 *
	 * @param sDateTime
	 *            yyyy-MM-dd HH:mm:ss.SSS
	 * @return
	 */
	public static Date SDateTimeToDateBySSS(String sDateTime) {
		return StrToDate(sDateTime, "yyyy-MM-dd HH:mm:ss.SSS");
	}

	/**
	 * @param sDateTime
	 *            yyyyMMddHHmmss
	 * @return
	 */
	public static Date sDateTimeToDate(String sDateTime) {
		return StrToDate(sDateTime, "yyyyMMddHHmmss");
	}

	// ***************************************************************************


	/**
	 * 日期相减
	 *
	 * @param date
	 *            日期
	 * @param val
	 *            天数
	 * @return 返回相减后的日期
	 */
	public static Date subDate(Date date, int val) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.DATE, -val);
		return gc.getTime();
	}

	public static Date subHour(Date date, int val) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.HOUR, -val);
		return gc.getTime();
	}

	public static Date subMinute(Date date, int val) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.MINUTE, -val);
		return gc.getTime();
	}

	/**
	 * 根据 date 判断这一天是这一年的第几天
	 *
	 * @param date
	 * @return
	 */
	public static int getDayOfYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_YEAR);
	}

	// ***************************************************************************

	/**
	 * 计算某一月份的最大天数
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static int sumDayByYearMonth(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.clear();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1); // 注意,Calendar对象默认一月为0
		return c.getActualMaximum(Calendar.DAY_OF_MONTH); // month 月份的天数
	}

	/**
	 * 得到某月的最后一天
	 *
	 * @param sDate1
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(c.DATE, c.getActualMaximum(c.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 * 得到某月的最后一天
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getLastDayOfMonth(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.set(c.YEAR, year);
		c.set(c.MONTH, month - 1);
		c.set(c.DATE, c.getActualMaximum(c.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 * 得到某月的第一天
	 *
	 * @param sDate1
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(c.DATE, c.getActualMinimum(c.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 * 得到某月的第一天
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getFirstDayOfMonth(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.set(c.YEAR, year);
		c.set(c.MONTH, month - 1);
		c.set(c.DATE, c.getActualMinimum(c.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 * 根据 开始日期 与 结束日期 得到它们之间的所有日期 包括 开始日期 与 结束日期
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List<Date> getDateList(Date startDate, Date endDate) {
		Date tempDate = startDate;
		List<Date> dateList = new ArrayList();
		if (isSameDay(startDate, endDate)) {
			dateList.add(tempDate);
		} else {
			while (tempDate.before(endDate)) {
				dateList.add(tempDate);
				tempDate = DateUtils.addDays(tempDate, 1);
			}
			dateList.add(tempDate);
		}
		return dateList;
	}

	/**
	 * 根据 开始日期 与 结束日期 得到它们之间的所有日期 包括 开始日期 与 结束日期
	 *
	 * @param startDate
	 *            - 开始日期 yyyy-MM-dd
	 * @param endDate
	 *            - 结束日期 yyyy-MM-dd
	 * @return List<yyyy-MM-dd>
	 */
	public static List<String> getDateToStrList(String startDate, String endDate) {
		return getDateList(startDate, endDate, "yyyy-MM-dd");
	}

	/**
	 * 根据 开始日期 与 结束日期 得到它们之间的所有日期 包括 开始日期 与 结束日期
	 *
	 * @param startDate
	 *            - 开始日期
	 * @param endDate
	 *            - 结束日期
	 * @param format
	 *            - 定义 startDate, endDate 及 返回数据 的格式
	 * @return
	 */
	public static List<String> getDateList(String startDate, String endDate,
			String format) {
		List<String> sDateList = new ArrayList<String>();
		Date periodDate = StrToDate(startDate, format);
		if (startDate.equals(endDate)) {
			sDateList.add(DateToStr(periodDate, format));
		} else {
			while (periodDate.before(StrToDate(endDate, format))) {
				sDateList.add(DateToStr(periodDate, format));
				periodDate = DateUtils.addDays(periodDate, 1);
			}
			sDateList.add(DateToStr(periodDate, format));
		}
		return sDateList;
	}

	/**
	 * 根据年月得到 month 月所有的日期
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static List<Date> getYearMonthAllDate(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		List<Date> dateList = new ArrayList<Date>();
		int size = c.getActualMaximum(Calendar.DATE);
		for (int i = 0; i < size; i++) {
			c.set(Calendar.DATE, i + 1);
			dateList.add(c.getTime());
		}
		return dateList;
	}

	/**
	 * 判断某年是否为闰年
	 *
	 * @param year
	 * @return
	 */
	public boolean isLeapYear(int year) {
		GregorianCalendar gc = new GregorianCalendar();
		return gc.isLeapYear(year);
	}

	/**
	 * 判断某年是否为闰年
	 *
	 * @param date
	 * @return
	 */
	public boolean isLeapYear(Date date) {
		GregorianCalendar gc = new GregorianCalendar();
		return gc.isLeapYear(YearToInt(date));
	}

	/**
	 * 相隔有多少天
	 *
	 * @param sd
	 * @param ed
	 * @return
	 */
	public static int getDays(Date sd, Date ed) {
		return (int) ((ed.getTime() - sd.getTime()) / (24 * 3600 * 1000));
	}

	/**
	 * 合并日期和时间
	 *
	 * @param date
	 * @param time
	 * @return
	 */
	public static Calendar mergeDateTime(Date date, java.sql.Time time) {
		Calendar cal = Calendar.getInstance();
		if (date != null)
			cal.setTime(date);
		if (time != null) {
			Calendar temp = Calendar.getInstance();
			temp.setTime(time);
			cal.set(Calendar.HOUR_OF_DAY, temp.get(Calendar.HOUR_OF_DAY));
			cal.set(Calendar.MINUTE, temp.get(Calendar.MINUTE));
			cal.set(Calendar.SECOND, temp.get(Calendar.SECOND));
			cal.set(Calendar.MILLISECOND, temp.get(Calendar.MILLISECOND));
		}
		return cal;
	}

	/**
     * 计算两个日期之间相差的天数
     * @param smdate 开始时间
     * @param bdate  结束时间
     * @return 相差天数
     * @throws ParseException
     * old
     * return (int) (d1.getTime() - d2.getTime()) / 86400000;
     */
	public static int diff_in_date(Date smdate, Date bdate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			smdate = sdf.parse(sdf.format(smdate));
			bdate = sdf.parse(sdf.format(bdate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time1 - time2) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 获取某天开始的那一刻
	 *
	 * @param year
	 * @param month
	 * @param date
	 * @return
	 */
	public static Calendar getDateBegin(int year, int month, int date) {
		Calendar begin_time = Calendar.getInstance();
		begin_time.set(Calendar.YEAR, year);
		begin_time.set(Calendar.MONTH, month - 1);
		begin_time.set(Calendar.DATE, date);
		begin_time.set(Calendar.HOUR_OF_DAY, 0);
		begin_time.set(Calendar.MINUTE, 0);
		begin_time.set(Calendar.SECOND, 0);
		begin_time.set(Calendar.MILLISECOND, 0);
		return begin_time;
	}

	/**
	 * 清除日历的时间字段
	 *
	 * @param cal
	 */
	public static void resetTime(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
	}

	// ***************************************************************************

	/**
	 * Checks if two date objects are on the same day ignoring time
	 *
	 * @param date1
	 *            the first date, not altered, not null
	 * @param date2
	 *            the second date, not altered, not null
	 * @return true if they represent the same day
	 * @throws IllegalArgumentException
	 *             if either date is <code>null</code>
	 */
	public static boolean isSameDay(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return isSameDay(cal1, cal2);
	}

	/**
	 * Checks if two calendar objects are on the same day ignoring time
	 *
	 * @param cal1
	 *            the first calendar, not altered, not null
	 * @param cal2
	 *            the second calendar, not altered, not null
	 * @return true if they represent the same day
	 * @throws IllegalArgumentException
	 *             if either calendar is <code>null</code>
	 */
	public static boolean isSameDay(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)
				&& cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1
					.get(Calendar.DAY_OF_YEAR) == cal2
				.get(Calendar.DAY_OF_YEAR));
	}

	/**
	 * Checks if two date objects represent the same instant in time. This
	 * method compares the long millisecond time of the two objects.
	 *
	 * @param date1
	 *            the first date, not altered, not null
	 * @param date2
	 *            the second date, not altered, not null
	 * @return true if they represent the same millisecond instant
	 * @throws IllegalArgumentException
	 *             if either date is <code>null</code>
	 */
	public static boolean isSameInstant(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		return date1.getTime() == date2.getTime();
	}

	/**
	 * Checks if two calendar objects represent the same instant in time. This
	 * method compares the long millisecond time of the two objects.
	 *
	 * @param cal1
	 *            the first calendar, not altered, not null
	 * @param cal2
	 *            the second calendar, not altered, not null
	 * @return true if they represent the same millisecond instant
	 * @throws IllegalArgumentException
	 *             if either date is <code>null</code>
	 */
	public static boolean isSameInstant(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		return cal1.getTime().getTime() == cal2.getTime().getTime();
	}

	/**
	 * Checks if two calendar objects represent the same local time. This method
	 * compares the values of the fields of the two objects. In addition, both
	 * calendars must be the same of the same type.
	 *
	 * @param cal1
	 *            the first calendar, not altered, not null
	 * @param cal2
	 *            the second calendar, not altered, not null
	 * @return true if they represent the same millisecond instant
	 * @throws IllegalArgumentException
	 *             if either date is <code>null</code>
	 */
	public static boolean isSameLocalTime(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		return (cal1.get(Calendar.MILLISECOND) == cal2
				.get(Calendar.MILLISECOND)
				&& cal1.get(Calendar.SECOND) == cal2.get(Calendar.SECOND)
				&& cal1.get(Calendar.MINUTE) == cal2.get(Calendar.MINUTE)
				&& cal1.get(Calendar.HOUR) == cal2.get(Calendar.HOUR)
				&& cal1.get(Calendar.DAY_OF_YEAR) == cal2
						.get(Calendar.DAY_OF_YEAR)
				&& cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
				&& cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1
					.getClass() == cal2.getClass());
	}

	// ***************************************************************************

	/**
	 * 开始时间 start 在 结束时间 end 之前返回 true，否则 false
	 *
	 * @param start
	 * @param end
	 * @return
	 */
	public static boolean isDateBefore(Date start, Date end) {
		GregorianCalendar sgc = new GregorianCalendar();
		GregorianCalendar egc = new GregorianCalendar();
		sgc.setTime(start);
		egc.setTime(end);

		return sgc.before(egc);
	}

	/**
	 * 判断 date 在 开始时间 start 与 结束时间 end 之间返回 true, 否则 false
	 *
	 * @param start
	 *            - 开始时间
	 * @param end
	 *            - 结束时间
	 * @param date
	 *            - 要判断的时间
	 * @return
	 */
	public static boolean isDateBetweenStartAndEnd(Date start, Date end,
			Date date) {
		if ((isDateBefore(start, end)) && (isDateBefore(start, date))
				&& (isDateBefore(date, end))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param date
	 * @return 若指定时间在当前时间之后则返回true,否则返回false
	 */
	public static boolean afterCurDate(Date date) {
		GregorianCalendar now = new GregorianCalendar();
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);
		return c.after(now);
	}

	// -----------------------------------------------------------------------
	// Other

	public static String getMonthStr(String str) {
		String monthOfYear[] = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN",
				"JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };
		if ("01".equals(str)) {
			str = monthOfYear[0];
		} else if ("02".equals(str)) {
			str = monthOfYear[1];
		} else if ("03".equals(str)) {
			str = monthOfYear[2];
		} else if ("04".equals(str)) {
			str = monthOfYear[3];
		} else if ("05".equals(str)) {
			str = monthOfYear[4];
		} else if ("06".equals(str)) {
			str = monthOfYear[5];
		} else if ("07".equals(str)) {
			str = monthOfYear[6];
		} else if ("08".equals(str)) {
			str = monthOfYear[7];
		} else if ("09".equals(str)) {
			str = monthOfYear[8];
		} else if ("10".equals(str)) {
			str = monthOfYear[9];
		} else if ("11".equals(str)) {
			str = monthOfYear[10];
		} else if ("12".equals(str)) {
			str = monthOfYear[11];
		} else {
			str = "ERROR";
		}
		return str;
	}

	/**
	 * 根据 英文月份全称 得到 数字 如: January => 1 </br>若返回为 0 即表明 strMonth 非月份字符串
	 *
	 * @param strMonth
	 * @return
	 */
	public static int getNumByStrMonth(String strMonth) {
		if (strMonth.equals("January")) {
			return 1;
		} else if (strMonth.equals("February")) {
			return 2;
		} else if (strMonth.equals("March")) {
			return 3;
		} else if (strMonth.equals("April")) {
			return 4;
		} else if (strMonth.equals("May")) {
			return 5;
		} else if (strMonth.equals("June")) {
			return 6;
		} else if (strMonth.equals("July")) {
			return 7;
		} else if (strMonth.equals("August")) {
			return 8;
		} else if (strMonth.equals("September")) {
			return 9;
		} else if (strMonth.equals("October")) {
			return 10;
		} else if (strMonth.equals("November")) {
			return 11;
		} else if (strMonth.equals("December")) {
			return 12;
		} else {
			return 0; // strMonth 非 月份
		}
	}

	public static String IntegerTo(Integer i) {
		String str = "";
		if (i < 10) {
			str = "0" + i;
		} else {
			str = String.valueOf(i);
		}
		return str;
	}

	/**
	 * 返回日期是多少(分钟)前
	 *
	 * @param time
	 * @return
	 */
	/**
	 *
	 public static String getShortTime(String time) { calendar =
	 * Calendar.getInstance(); calendar.setTime(DateUtil.SDateTimeToDate(time));
	 * String shortstring =null; long now =
	 * Calendar.getInstance().getTimeInMillis(); Date date =
	 * DateUtil.SDateTimeToDate(time); if(date ==null)return shortstring; long
	 * deltime = (now - date.getTime())/1000; if(deltime > 365*24*60*60) { //
	 * shortstring = (int)(deltime/(365*24*60*60)) +"年前"; shortstring = time;
	 * }else if(deltime > 24*60*60) { // shortstring = (int)(deltime/(24*60*60))
	 * +"天前"; // System.err.println("******"+deltime/(24*60*60));
	 * if((int)(deltime/(24*60*60))<=1){
	 * System.err.println((int)(deltime/(24*60*60))); //
	 * System.err.println("昨天 "
	 * +calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE));
	 * shortstring =
	 * "昨天 "+IntegerTo(calendar.get(Calendar.HOUR_OF_DAY))+":"+IntegerTo
	 * (calendar.get(Calendar.MINUTE)); }else if((int)(deltime/(24*60*60))<=2){
	 * // System.err.println("前天 "+calendar.get(Calendar.HOUR_OF_DAY)+":"+
	 * calendar.get(Calendar.MINUTE)); shortstring =
	 * "前天 "+IntegerTo(calendar.get
	 * (Calendar.HOUR_OF_DAY))+":"+IntegerTo(calendar.get(Calendar.MINUTE));
	 * }else if((int)(deltime/(24*60*60))<=186){ // System.err.println(time);
	 * shortstring =
	 * IntegerTo((calendar.get(Calendar.MONTH)+1))+"月"+IntegerTo(calendar
	 * .get(Calendar.DAY_OF_MONTH))+"日 "
	 * +IntegerTo(calendar.get(Calendar.HOUR_OF_DAY
	 * ))+":"+IntegerTo(calendar.get(Calendar.MINUTE)); }else{ shortstring =
	 * time; } }else if(deltime > 60*60) { if((int)(deltime/(60*60))<12){
	 * shortstring = (int)(deltime/(60*60)) +"小时前"; } // else
	 * if((int)(deltime/(60*60))<24){ // shortstring =
	 * "昨天 "+IntegerTo(calendar.get
	 * (Calendar.HOUR_OF_DAY))+":"+IntegerTo(calendar.get(Calendar.MINUTE)); //
	 * } // else{ // shortstring =
	 * "今天 "+IntegerTo(calendar.get(Calendar.HOUR_OF_DAY
	 * ))+":"+IntegerTo(calendar.get(Calendar.MINUTE)); // } }else if(deltime >
	 * 60) { shortstring = IntegerTo((int)(deltime/(60))) +"分前"; }else
	 * if(deltime > 1) { shortstring = deltime +"秒前"; }else{ shortstring ="1秒前";
	 * } return shortstring; }
	 */
	public static String getTime(Date date) {
		String todySDF = "今天 HH:mm";
		String yesterDaySDF = "昨天 HH:mm";
		String otherSDF = "M月d日 HH:mm";
		SimpleDateFormat sfd = null;
		String time = "";
		Calendar dateCalendar = Calendar.getInstance();
		dateCalendar.setTime(date);
		Date now = new Date();
		Calendar targetCalendar = Calendar.getInstance();
		targetCalendar.setTime(now);
		targetCalendar.set(Calendar.HOUR_OF_DAY, 0);
		targetCalendar.set(Calendar.MINUTE, 0);
		if (dateCalendar.after(targetCalendar)) {
			sfd = new SimpleDateFormat(todySDF);
			time = sfd.format(date);
			return time;
		} else {
			targetCalendar.add(Calendar.DATE, -1);
			if (dateCalendar.after(targetCalendar)) {
				sfd = new SimpleDateFormat(yesterDaySDF);
				time = sfd.format(date);
				return time;
			}
		}
		sfd = new SimpleDateFormat(otherSDF);
		time = sfd.format(date);
		return time;
	}

	/**
	 * 将日期格式的字符串转换为长整型
	 *
	 * @param date
	 * @param format
	 * @return
	 */
	public static long convert2long(String date, String format) {
		try {
			if (StringUtils.isNotBlank(date)) {
				SimpleDateFormat sf = new SimpleDateFormat(format);
				return sf.parse(date).getTime();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0l;
	}

	/**
	 * 将长整型数字转换为日期格式的字符串
	 *
	 * @param time
	 * @param format
	 * @return
	 */
	public static String convert2String(long time, String format) {
		if (time > 0l) {
			SimpleDateFormat sf = new SimpleDateFormat(format);
			Date date = new Date(time);
			return sf.format(date);
		}
		return "";
	}

	/**
	 * 获取当前系统的日期
	 *
	 * @return
	 */
	public static long curTimeMillis() {
		return System.currentTimeMillis();
	}

	/**
	 * @param dateFormat
	 * @param days
	 * @param l
	 * @return
	 */
	public static List<String> getLastDays (String dateFormat,int days,List<String> l) {
		return getLastDays(dateFormat,days,true,l);
	}
	/**
	 * @param dateFormat
	 * @param days
	 * @param dir 是否排序,true降序,false升序
	 * @param l
	 * @return
	 */
	public static List<String> getLastDays (String dateFormat,int days,boolean dir,List<String> l) {
		List<String> list = new LinkedList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);//转换格式
		Calendar cal = Calendar.getInstance();
		for (int i=0;i<days;i++) {
			cal.add(Calendar.DAY_OF_YEAR, -1);
			String date = sdf.format(cal.getTime());
			list.add(date);
			l.add("0");
		}
		if (dir)
			Collections.reverse(list);
		return list;
	}

	/**
	 * 获取指定格式的日期列表
	 * @param format 	日期格式
	 * @param date		开始日期
	 * @param size		总共天数
	 * @return
	 */
	public static List<String> getDurationDays(String format, Date date, int size) {
		List<String> list = new LinkedList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		for (int i=0; i<size; i++) {
			list.add(sdf.format(cal.getTime()));
			cal.add(Calendar.DAY_OF_YEAR, 1);
		}

		return list;
	}

	public static List<String> getDurationDays(String format, Date begDate, Date endDate) {
		// 时间间隔超过一年
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(endDate.getTime() - begDate.getTime()));
		if(cal.get(Calendar.YEAR) - 1970 > 0) {
			throw new IllegalArgumentException("时间间隔超过一年");
		}

		//
		Calendar begCal = Calendar.getInstance();
		begCal.setTime(begDate);
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);

		//
		int size = endCal.get(Calendar.DAY_OF_YEAR) - begCal.get(Calendar.DAY_OF_YEAR);
		return getDurationDays(format, begDate, size + 1);
	}

	/** 在当前时间内增加秒数
	 * @param second
	 * @return
	 */
	public static Date addSecond (int second) {
		Calendar calendar = Calendar.getInstance ();
		calendar.add (Calendar.MILLISECOND, second);
		return calendar.getTime();
	}


	// ***********************************************************************************************************
	/**
	 *
	 * @description:时间戳转换日期
	 * @author: wangzuo chun
	 * @version:  v0.1
	 * @time: 2018年5月31日 下午3:09:04
	 *  @param time
	 *  @return
	 */
	public static Date timeStampToDate(Long time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = format.format(time);
		Date date = null;
		try {
			date = format.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;

	}

	/**
	 * @param date
	 * @return String yyyyMMddHHmmssSSS
	 */
	public static String DateMillisecondToStr(Date date) {
		return DateToStr(date, "yyyyMMddHHmmssSSS");
	}

	/**
	 * @param date
	 * @return String yyyyMMddHHmmss
	 */
	public static String DatesecondToStr(Date date) {
		return DateToStr(date, "yyyyMMddHHmmss");
	}

	/** 获取当前时间
	 * @return String yyyy-MM-dd HH:mm:ss
	 */
	public static String getNowDateTime() {
		return DateToStr(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * @param date
	 * @return String yyyy-MM-dd HH:mm:ss
	 */
	public static String DateTimeToStr(Date date) {
		return DateToStr(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * @param date
	 * @return String yyyy-MM-dd
	 */
	public static String DateTimeToStr2(Date date) {
		return DateToStr(date, "yyyy-MM-dd");
	}

	/**
	 * @param date
	 * @return String yyyyMMdd
	 */
	public static String DateTimeToStrM(Date date) {
		return DateToStr(date, "yyyyMMdd");
	}

	/**
	 * @param date
	 * @return String yyyy-MM-dd HH:mm
	 */
	public static String DateTimeToStr3(Date date) {
		return DateToStr(date, "yyyy-MM-dd HH:mm");
	}

	/**
	 * @param date HH:mm
	 * @return String HH:mm
	 */
	public static String DateTimeToStrHour(Date date) {
		return DateToStr(date, "HH:mm");
	}

	/**
	 * @param date
	 * @param format
	 *            日期格式
	 * @return String
	 */
	public static String DateToStr(Date date, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	* 字符串转换成日期 yyyy-MM-dd HH:mm:ss
	* @param str
	*/
	public static Date StrToDateTime(String str) {
		return StrToDate(str, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	* 字符串转换成日期  HH:mm
	* @param str
	*/
	public static Date StrToDateTimeHours(String str) {
		return StrToDate(str, "HH:mm");
	}

	/**
	* 字符串转换成日期
	* @param str
	* @return format
	*/
	public static Date StrToDate(String str, String format) {

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}


	/**
	* 字符串转换成日期  yyyy-MM-dd HH:mm
	* @param str
	* @return date
	*/
	public static Date StrToDate(String str) {
		return StrToDate(str, "yyyy-MM-dd HH:mm");
	}

	/**
	* 字符串转换成日期  yyyy-MM-dd
	* @param str
	* @return date
	*/
	public static Date StrToDate2(String str) {
		return StrToDate(str, "yyyy-MM-dd");
	}

	/**
	* 字符串转换成日期
	* @param str
	* @return date
	*/
	public static Date StrToDate3(String str) {
		return StrToDate(str, "yyyyMMdd");
	}

	/**
	 * 分钟相加
	 *
	 * @param date
	 *            日期
	 * @param val
	 *            分钟数
	 * @return 返回相加后的日期
	 */
	public static Date addMinute(Date date, int val) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.MINUTE, val);
		return gc.getTime();
	}

	/**
	 * 秒数相加
	 *
	 * @param date
	 *            日期
	 * @param val
	 *            分钟数
	 * @return 返回相加后的日期
	 */
	public static Date addSecond(Date date, int val) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.SECOND, val);
		return gc.getTime();
	}



	/**
	 * 小时相加
	 *
	 * @param date
	 *            日期
	 * @param val
	 *            分钟数
	 * @return 返回相加后的日期
	 */
	public static Date addHour(Date date, int val) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.HOUR, val);
		return gc.getTime();
	}

	/**
	 * 月份相加
	 *
	 * @param date
	 *            日期
	 * @param val
	 *            分钟数
	 * @return 返回相加后的日期
	 */
	public static Date addMonth(Date date, int val) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.MONTH, val);
		return gc.getTime();
	}

	/**
	 * 天数相加
	 *
	 * @param date
	 *            日期
	 * @param val
	 *            天数
	 * @return 返回相加后的日期
	 */
	public static Date addDate(Date date, int val) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.DATE, val);
		return gc.getTime();
	}

	/**
	 *
	 * @description:年份相加
	 * @author: wangzuo chun
	 * @version:  v0.1
	 * @time: 2018年7月26日 上午8:40:34
	 *  @param date
	 *  @param val
	 *  @return
	 */
	public static Date addYear(Date date, int val) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.YEAR, val);
		return gc.getTime();
	}

	/**
	 * 两个时间做比较
	 * @description:
	 * @author: dongrongchang
	 * @version:  v0.1
	 * @time: 2018年5月13日 上午10:18:14
	 *  @param dt1
	 *  @param dt2
	 *  @return
	 */
	public static int compareDate(Date dt1,Date dt2){
        if (dt1.getTime() > dt2.getTime()) {// dt1 比dt2大
            return 1;
        } else if (dt1.getTime() < dt2.getTime()) {// dt1比dt2小
            return -1;
        } else {//相等
            return 0;
        }
	}


	/**
	 * 两个时间相差距离多少天多少小时多少分多少秒
	 *
	 * @param str1
	 *            时间参数 1 格式：1990-01-01 12:00
	 * @param str2
	 *            时间参数 2 格式：2009-01-01 12:00s
	 * @return long[] 返回值为：{天, 时, 分, 秒}
	 */
	public static long[] getDistanceTimes(String str1, String str2){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date one;
		Date two;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		try {
			one = df.parse(str1);
			two = df.parse(str2);
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff;
			if (time1 < time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			day = diff / (24 * 60 * 60 * 1000);
			hour = (diff / (60 * 60 * 1000) - day * 24);
			min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
			sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long[] times = { day, hour, min, sec };
		return times;
	}

	/**
	 * 计算2个日期之间相差的  以年、月、日为单位，各自计算结果是多少
	 * 比如：2011-02-02 到  2017-03-02
	 *                                以月为单位相差为：73个月
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public static int getDistanceMonths(Date fromDate, Date toDate){
		Calendar  from  =  Calendar.getInstance();
		from.setTime(fromDate);
		Calendar  to  =  Calendar.getInstance();
		to.setTime(toDate);
		//只要年月
		int fromYear = from.get(Calendar.YEAR);
		int fromMonth = from.get(Calendar.MONTH);

		int toYear = to.get(Calendar.YEAR);
		int toMonth = to.get(Calendar.MONTH);

//		int year = toYear - fromYear;// 相差多少年
		int month = toYear *  12  + toMonth  -  (fromYear  *  12  +  fromMonth);// 相差多少个月
//		int day = (int) ((to.getTimeInMillis()  -  from.getTimeInMillis())  /  (24  *  3600  *  1000));// 相差多少天
		return month;
	}

	/**
	 * 计算2个日期之间相差的  以年、月、日为单位，根据天数计算，大于0，月数进1，否则反之
	 * 比如：2011-02-02 到  2017-03-02
	 *                                以月为单位相差为：73个月
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public static int getDistanceMonthsRounds(Date fromDate, Date toDate){
		Calendar  from  =  Calendar.getInstance();
		from.setTime(fromDate);
		Calendar  to  =  Calendar.getInstance();
		to.setTime(toDate);
		//只要年月日
		int fromYear = from.get(Calendar.YEAR);
		int fromMonth = from.get(Calendar.MONTH);
		int fromDay = from.get(Calendar.DAY_OF_MONTH);

		int toYear = to.get(Calendar.YEAR);
		int toMonth = to.get(Calendar.MONTH);
		int toDay = to.get(Calendar.DAY_OF_MONTH);

		int month = toYear *  12  + toMonth  -  (fromYear  *  12  +  fromMonth);// 相差多少个月
		int day = toDay - fromDay;
		if (day > 0) {
			month = month + 1;
		} else if (day < 0) {
			month = month - 1;
		} else {
		}
		return month;
	}

	/** 验证时间是否在今天之后
	 * @param date 待验证时间
	 * @return
	 */
	public static boolean afterToday(Date date){
		Date now = DateUtil.StrToDate(DateUtil.DateTimeToStr(new Date()));
		return afterDate(date, now);
	}

	/** 开始时间是否在结束时间之后
	 * @param beginDate 开始时间
	 * @param endDate 结束时间
	 * @return
	 */
	public static boolean afterDate(Date beginDate, Date endDate){
		GregorianCalendar begin = new GregorianCalendar();
		GregorianCalendar end = new GregorianCalendar();
		begin.setTime(beginDate);
		end.setTime(endDate);
		return begin.after(end);
	}

	/**
	 * 两个时间相差距离多少秒
	 * @param start
	 * @param end
	 * @return
	 */
	public static long getSpanSecond(Date start, Date end){
		long endTime = end.getTime();
		long startTime = start.getTime();
		return (endTime - startTime)/1000;
	}

	/**
	 * 两个时间相差距离多少分
	 * @param start
	 * @param end
	 * @return
	 */
	public static long getSpanMinute (Date start, Date end){
		return getSpanSecond(start, end)/60;
	}

	/**
	 * 两个时间相差距离多少小时
	 * @param start
	 * @param end
	 * @return
	 */
	public static long getSpanHour (Date start, Date end){
		return getSpanMinute(start, end)/60;
	}

	/**
	 * 两个时间相差距离多少天
	 * @param start
	 * @param end
	 * @return
	 */
	public static long getSpanDay (Date start, Date end){
		return getSpanHour(start, end)/24;
	}

	/**
	 * 格式化yyyy-MM-dd为 XX月XX日
	 * @param date
	 * @return
	 */
	public static String formatDateToMonthDay (String date){
		StringBuffer sb = new StringBuffer();
		String[] result = date.split("-");
		sb.append(result[1]+"月");
		sb.append(result[2]+"日");
		return sb.toString();
	}


	 /** date类型转换为String类型
	 * @description:
	 * @author: huangziwang
	 * @version:  v0.1
	 * @time: 2018年6月1日 下午8:13:17
	 *  @param data Date类型的时间
	 *  @param formatType 格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
	 *  @return
	 */
	public static String dateToString(Date data, String formatType) {
		return new SimpleDateFormat(formatType).format(data);
	}

	 /**
	 * @description:long类型转换为String类型
	 * @author: huangziwang
	 * @version:  v0.1
	 * @time: 2018年6月1日 下午8:13:36
	 *  @param currentTime 要转换的long类型的时间
	 *  @param formatType 要转换的string类型的时间格式
	 *  @return
	 *  @throws ParseException
	 */
	public static String longToString(long currentTime, String formatType)
			throws ParseException {
		Date date = longToDate(currentTime, formatType); // long类型转成Date类型
		String strTime = dateToString(date, formatType); // date类型转成String
		return strTime;
	}

	 /**
	 * @description:string类型转换为date类型
	 * @author: huangziwang
	 * @version:  v0.1
	 * @time: 2018年6月1日 下午8:14:00
	 *  @param strTime 要转换的string类型的时间
	 *  @param formatType 要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日HH时mm分ss秒
	 *  strTime的时间格式必须要与formatType的时间格式相同
	 *  @return
	 *  @throws ParseException
	 */
	public static Date stringToDate(String strTime, String formatType)
			throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(formatType);
		Date date = null;
		date = formatter.parse(strTime);
		return date;
	}

	 /**
	 * @description:long转换为Date类型
	 * @author: huangziwang
	 * @version:  v0.1
	 * @time: 2018年6月1日 下午8:14:46
	 *  @param currentTime 要转换的long类型的时间
	 *  @param formatType 要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
	 *  @return
	 *  @throws ParseException
	 */
	public static Date longToDate(long currentTime, String formatType)
			throws ParseException {
		Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
		String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
		Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
		return date;
	}

	 /**
	 * @description:string类型转换为long类型
	 * @author: huangziwang
	 * @version:  v0.1
	 * @time: 2018年6月1日 下午8:15:02
	 *  @param strTime 要转换的String类型的时间
	 *  @param formatType 时间格式
	 *  @return strTime的时间格式和formatType的时间格式必须相同
	 *  @throws ParseException
	 */
	public static long stringToLong(String strTime, String formatType)
			throws ParseException {
		Date date = stringToDate(strTime, formatType); // String类型转成date类型
		if (date == null) {
			return 0;
		} else {
			long currentTime = dateToLong(date); // date类型转成long类型
			return currentTime;
		}
	}

	 /**
	 * @description:date类型转换为long类型
	 * @author: huangziwang
	 * @version:  v0.1
	 * @time: 2018年6月1日 下午8:15:30
	 *  @param date 要转换的date类型的时间
	 *  @return
	 */
	public static long dateToLong(Date date) {
		return date.getTime();
	}

	 /**
	 * @description: 数值1大于数值2
	 * @author: huangziwang
	 * @version:  v0.1
	 * @time: 2018年6月3日 下午2:41:11
	 *  @param value1
	 *  @param value2
	 *  @return
	 */
	public static boolean value1DayuValue2(long value1, long value2) {
		return value1 > value2;
	}

	 /**
	 * @description: 获取当前时间戳到分钟
	 * @author: huangziwang
	 * @version:  v0.1
	 * @time: 2018年6月3日 下午2:56:16
	 *  @return
	 * @throws ParseException
	 */
	public static long getMinute() {
		String dateStr = DateTimeToStr3(new Date());
		return StrToDate(dateStr).getTime();
	}

	 /**
	 * @description: 获取当前时间戳到秒
	 * @author: huangziwang
	 * @version:  v0.1
	 * @time: 2018年6月3日 下午2:56:16
	 *  @return
	 * @throws ParseException
	 */
	public static long getSecond() {
		String dateStr = DateTimeToStr(new Date());
		return StrToDateTime(dateStr).getTime();
	}

	/**
	 * @description 获取今年是哪一年
	 * @author: huangziwang
	 * @version: v0.1
	 * @time: 2018年7月17日 下午9:46:26
	 * @return
	 */
	public static Integer getNowYear() {
		Date date = new Date();
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		return Integer.valueOf(gc.get(1));
	}

	/**
	 * @description 获取本月是哪一月
	 * @author: huangziwang
	 * @version: v0.1
	 * @time: 2018年7月17日 下午9:46:35
	 * @return
	 */
	public static int getNowMonth() {
		Date date = new Date();
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		return gc.get(2) + 1;
	}

	/**
	 * @description 获取本月的开始时间 (yyyy-MM-dd 00:00:00)
	 * @author: huangziwang
	 * @version: v0.1
	 * @time: 2018年7月17日 下午9:46:45
	 * @return
	 */
	public static String getBeginDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(getNowYear(), getNowMonth() - 1, 1);
		return DateUtil.DateTimeToStr(getDayStartTime(calendar.getTime()));
	}

	/**
	 * @description 获取本月的结束时间 (yyyy-MM-dd 23:59:59)
	 * @author: huangziwang
	 * @version: v0.1
	 * @time: 2018年7月17日 下午9:50:25
	 * @return
	 */
	public static String getEndDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(getNowYear(), getNowMonth() - 1, 1);
		int day = calendar.getActualMaximum(5);
		calendar.set(getNowYear(), getNowMonth() - 1, day);
		return DateUtil.DateTimeToStr(getDayEndTime(calendar.getTime()));
	}

	/**
	 * @description 获取某个日期的开始时间（00:00:00）
	 * @author: huangziwang
	 * @version: v0.1
	 * @time: 2018年7月17日 下午9:49:03
	 * @param d
	 * @return
	 */
	public static Date getDayStartTime(Date d) {
		Calendar calendar = Calendar.getInstance();
		if (null != d) {
			calendar.setTime(d);
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			calendar.set(Calendar.MILLISECOND, 0);
		}
		return calendar.getTime();
	}

	/**
	 * @description 获取某个日期的结束时间(23:59:59)
	 * @author: huangziwang
	 * @version: v0.1
	 * @time: 2018年7月17日 下午9:49:54
	 * @param d
	 * @return
	 */
	public static Date getDayEndTime(Date d) {
		Calendar calendar = Calendar.getInstance();
		if (null != d) {
			calendar.setTime(d);
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
			calendar.set(Calendar.MILLISECOND, 999);
		}
		return calendar.getTime();
	}


	/**
	 * @description 获取某个日期的开始时间（00:00:00）
	 * @version: v0.1
	 * @time: 2018年7月17日 下午9:49:03
	 * @param d
	 * @return
	 */
	public static long getDayStartTimeLong(Date d) {
		return getDayStartTime(d).getTime();
	}

	/**
	 * @description 获取某个日期的结束时间(23:59:59)
	 * @version: v0.1
	 * @time: 2018年7月17日 下午9:49:54
	 * @param d
	 * @return
	 */
	public static long getDayEndTimeLong(Date d) {
		return getDayEndTime(d).getTime();
	}

	public static String getWeekString(Integer week) {
		switch (week) {
		case 1:
			return "日";
		case 2:
			return "一";
		case 3:
			return "二";
		case 4:
			return "三";
		case 5:
			return "四";
		case 6:
			return "五";
		case 7:
			return "六";
		default:
			break;
		}
		return "";
	}

	public static String getWeekString(String date) {
		Calendar cal = Calendar.getInstance();
		String[] weekArray=new String[]{"日","一","二","三","四","五","六"};
		if (date.equals("")) {
			cal.setTime(new Date(System.currentTimeMillis()));
		} else {
			cal.setTime(new Date(getDateByStr2(date).getTime()));
		}
		return weekArray[cal.get(Calendar.DAY_OF_WEEK) - 1];
	}

	public static int getDayOfMonth(String date) {
		Calendar cal = Calendar.getInstance();
		if (date.equals("")) {
			cal.setTime(new Date(System.currentTimeMillis()));
		} else {
			cal.setTime(new Date(getDateByStr2(date).getTime()));
		}
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public static int getMonth(String date) {
		Calendar cal = Calendar.getInstance();
		if (date.equals("")) {
			cal.setTime(new Date(System.currentTimeMillis()));
		} else {
			cal.setTime(new Date(getDateByStr2(date).getTime()));
		}
		return cal.get(Calendar.MONTH) + 1;
	}

	public static Date getDateByStr2(String dd) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sd.parse(dd);
		} catch (Exception e) {
			date = null;
			e.printStackTrace();
		}
		return date;
	}

	/**
	 *
	 * @description:获取本周第一天时间
	 * @author: wangzuo chun
	 * @version:  v0.1
	 * @time: 2018年8月20日 上午11:15:20
	 *  @return
	 */
	public static Date getWeekStartDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date date = cal.getTime();
		return date;
	}

	/**
	 *
	 * @description获取本周最后一天日期
	 * @author: wangzuo chun
	 * @version:  v0.1
	 * @time: 2018年8月20日 上午11:26:39
	 *  @return
	 */
	public static Date getWeekEndDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		Date date = cal.getTime();
		return date;
	}

	 /**
	 * @description:时间戳转化成字符串yyyy-MM-dd HH:mm:ss格式
	 * @author: huangziwang
	 * @version:  v0.1
	 * @time: 2018年9月6日 下午5:24:40
	 *  @param time
	 *  @return
	 */
	public static String longToString(Long time) {

		Date date = timeStampToDate(time);

		return DateToStr(date, "yyyy-MM-dd HH:mm:ss");
	}


	/**
	 * @return验证当前时间是否在0点到7点之间，如果在的话返回true
	 * @throws ParseException
	 */
	public static boolean isTimeRange() throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		Date now = df.parse(df.format(new Date()));
		Date begin = df.parse("00:00");
		Date end = df.parse("07:00");
		Calendar nowTime = Calendar.getInstance();
		nowTime.setTime(now);
		Calendar beginTime = Calendar.getInstance();
		beginTime.setTime(begin);
		Calendar endTime = Calendar.getInstance();
		endTime.setTime(end);
		if (nowTime.before(endTime) && nowTime.after(beginTime)) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) throws ParseException {
		System.out.println(dateToString(addDate(new Date(), -3), "yyyy-MM-dd HH:mm:ss"));
		System.out.println(dateToLong(addDate(new Date(), -3)));
		System.out.println(getNowMonth());
		System.out.println(dateToLong(new Date(), "yyyy-MM-dd"));
	}

	 /**
	 * @description:时间转化为时间戳
	 * @author: huangziwang
	 * @version:  v0.1
	 * @time: 2019年2月16日 下午3:02:17
	 * @param date
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static long dateToLong(Date date,String format) throws ParseException {
		String s = DateToStr(date, format);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		Date dateTmp = simpleDateFormat.parse(s);
		long ts = dateTmp.getTime();
		return ts;
	}

	 /**
	 * @description:根据输入时间来获取和当前时间对比还剩余多少时间戳
	 * @author: huangziwang
	 * @version:  v0.1
	 * @time: 2019年5月17日 下午2:21:12
	 *  @param endDate
	 *  @return
	 */
	public static Long getSurplusTime (Date endDate) {
        if (DateUtil.afterDate(endDate, new Date())) {
            try {
                Long endTime = DateUtil.dateToLong(endDate ,"yyyy-MM-dd HH:mm:ss.SSS");
                return endTime - System.currentTimeMillis();
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return 0L;
    }

}

