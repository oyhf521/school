package io.school.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 日期处理工具类
 * 
 * @author guixin 2012-10-27 上午08:49
 */
public class DateUtils {

	private static final transient Log log = LogFactory.getLog(DateUtils.class);
	public static final String TIME_PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DAY = "yyyyMMdd";

	/**  */
	private static String[] regDate = { "yyyy-MM-dd", "yyyy/MM/dd", "MM/dd/yyyy", "dd/MM/yyyy", "yyyy-MM-dd HH:mm",
			"yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "MM/dd/yyyy HH:mm:ss", "dd/MM/yyyy HH:mm:ss",
			"MM/dd/yyyy HH:mm", "dd/MM/yyyy HH:mm" };
	/**
	 * 默认日期类型格试.
	 * 
	 * @see DAFAULT_DATE_FORMAT
	 */
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

	/**
	 * 时间格式
	 */

	private static SimpleDateFormat datetimeFormat = new SimpleDateFormat(TIME_PATTERN_DEFAULT);

	/**
	 * 缺省的时间格式
	 */
	private static final String DAFAULT_TIME_FORMAT = "HH:mm:ss";

	private static SimpleDateFormat timeFormat = new SimpleDateFormat(DAFAULT_TIME_FORMAT);

	private static final long ONE_MINUTE = 60000L;
	private static final long ONE_HOUR = 3600000L;
	private static final long ONE_DAY = 86400000L;

	private static final String ONE_SECOND_AGO = "1分钟前";
	private static final String ONE_MINUTE_AGO = "分钟前";
	private static final String ONE_HOUR_AGO = "小时前";
	private static final String ONE_DAY_AGO = "天前";

	private DateUtils() {
		// 私用构造主法.因为此类是工具类.
	}

	/**
	 * 获取格式化实例.
	 * 
	 * @param pattern
	 *            如果为空使用DAFAULT_DATE_FORMAT
	 * @return
	 */
	public static SimpleDateFormat getFormatInstance(String pattern) {
		if (pattern == null || pattern.length() == 0) {
			pattern = DATE_FORMAT;
		}
		return new SimpleDateFormat(pattern);
	}

	/**
	 * 格式化Calendar
	 * 
	 * @param calendar
	 * @return
	 */
	public static String formatCalendar(Calendar calendar) {
		if (calendar == null) {
			return "";
		}
		return dateFormat.format(calendar.getTime());
	}

	public static String formatDateTime(Date d) {
		if (d == null) {
			return "";
		}
		return datetimeFormat.format(d);
	}

	public static String formatDate(Date d) {
		if (d == null) {
			return "";
		}
		return dateFormat.format(d);
	}

	/**
	 * 格式化时间
	 * 
	 * @param calendar
	 * @return
	 */
	public static String formatTime(Date d) {
		if (d == null) {
			return "";
		}
		return timeFormat.format(d);
	}

	/**
	 * 格式化整数型日期
	 * 
	 * @param intDate
	 * @return
	 */
	public static String formatIntDate(Integer intDate) {
		if (intDate == null) {
			return "";
		}
		Calendar c = newCalendar(intDate);
		return formatCalendar(c);
	}

	/**
	 * 根据指定格式化来格式日期.
	 * 
	 * @param date
	 *            待格式化的日期.
	 * @param pattern
	 *            格式化样式或分格,如yyMMddHHmmss
	 * @return 字符串型日期.
	 */
	public static String formatDate(Date date, String pattern) {
		if (date == null) {
			return "";
		}
		if (StringUtils.isBlank(pattern)) {
			return formatDate(date);
		}
		SimpleDateFormat simpleDateFormat = null;
		try {
			simpleDateFormat = new SimpleDateFormat(pattern);
		} catch (Exception e) {
			e.printStackTrace();
			return formatDate(date);
		}
		return simpleDateFormat.format(date);
	}

	/**
	 * 取得Integer型的当前日期
	 * 
	 * @return
	 */
	public static Integer getIntNow() {
		return getIntDate(new Date());
	}

	/**
	 * 取得Integer型的当前日期
	 * 
	 * @return
	 */
	public static Integer getIntToday() {
		return getIntDate(new Date());
	}

	/**
	 * 取得Integer型的当前年份
	 * 
	 * @return
	 */
	public static Integer getIntYearNow() {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		return year;
	}

	/**
	 * 取得Integer型的当前月份
	 * 
	 * @return
	 */
	public static Integer getIntMonthNow() {
		Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH) + 1;
		return month;
	}

	public static String getStringToday() {
		return getIntDate(new Date()) + "";
	}

	/**
	 * 根据年月日获取整型日期
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Integer getIntDate(int year, int month, int day) {
		return getIntDate(newCalendar(year, month, day));
	}

	/**
	 * 某年月的第一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Integer getFirstDayOfMonth(int year, int month) {
		return getIntDate(newCalendar(year, month, 1));
	}

	/**
	 * 某年月的第一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Integer getFirstDayOfThisMonth() {
		Integer year = getIntYearNow();
		Integer month = getIntMonthNow();
		return getIntDate(newCalendar(year, month, 1));
	}

	/**
	 * 某年月的第一天
	 * 
	 * @param date
	 * @return
	 * @time:2008-7-4 上午09:58:55
	 */
	public static Integer getFistDayOfMonth(Date date) {
		Integer intDate = getIntDate(date);
		int year = intDate / 10000;
		int month = intDate % 10000 / 100;
		return getIntDate(newCalendar(year, month, 1));
	}

	/**
	 * 某年月的最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Integer getLastDayOfMonth(int year, int month) {
		return intDateSub(getIntDate(newCalendar(year, month + 1, 1)), 1);
	}

	/**
	 * 根据Calendar获取整型年份
	 * 
	 * @param c
	 * @return
	 */
	public static Integer getIntYear(Calendar c) {
		int year = c.get(Calendar.YEAR);
		return year;
	}

	/**
	 * 根据Calendar获取整型日期
	 * 
	 * @param c
	 * @return
	 */
	public static Integer getIntDate(Calendar c) {
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		return year * 10000 + month * 100 + day;
	}

	/**
	 * 根据Date获取整型年份
	 * 
	 * @param d
	 * @return
	 */
	public static Integer getIntYear(Date d) {
		if (d == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return getIntYear(c);
	}

	/**
	 * 根据Date获取整型日期
	 * 
	 * @param d
	 * @return
	 */
	public static Integer getIntDate(Date d) {
		if (d == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return getIntDate(c);
	}

	/**
	 * 根据Integer获取Date日期
	 * 
	 * @param n
	 * @return
	 */
	public static Date getDate(Integer n) {
		if (n == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.set(n / 10000, n / 100 % 100 - 1, n % 100);
		return c.getTime();
	}

	public static Date getDate(String date) {
		if (date == null || date.length() == 0) {
			return null;
		}

		try {
			if (date.contains("/")) {
				date = date.replaceAll("/", "-");
			}
			return getFormatInstance(DATE_FORMAT).parse(date);
		} catch (ParseException e) {
			log.error("解析[" + date + "]错误！", e);
			return null;
		}
	}

	/**
	 * 根据年份Integer获取Date日期
	 * 
	 * @param year
	 * @return
	 */
	public static Date getFirstDayOfYear(Integer year) {
		if (year == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.set(year, 1, 1);
		return c.getTime();
	}

	/**
	 * 根据年月日生成Calendar
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Calendar newCalendar(int year, int month, int day) {
		Calendar ret = Calendar.getInstance();
		if (year < 100) {
			year = 2000 + year;
		}
		ret.set(year, month - 1, day);
		return ret;
	}

	/**
	 * 根据整型日期生成Calendar
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar newCalendar(int date) {
		int year = date / 10000;
		int month = (date % 10000) / 100;
		int day = date % 100;

		Calendar ret = Calendar.getInstance();
		ret.set(year, month - 1, day);
		return ret;
	}

	/**
	 * 取得Date型的当前日期
	 * 
	 * @return
	 */
	public static Date getNow() {
		return parse(getDateString());
	}

	/**
	 * 取得Date型的当前日期
	 * 
	 * @return
	 */
	public static Date getToday() {
		return getDate(getIntToday());
	}

	/**
	 * 整数型日期的加法
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Integer intDateAdd(int date, int days) {
		int year = date / 10000;
		int month = (date % 10000) / 100;
		int day = date % 100;

		day += days;

		return getIntDate(year, month, day);
	}

	/**
	 * 整数型日期的减法
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Integer intDateSub(int date, int days) {
		return intDateAdd(date, -days);
	}

	/**
	 * 计算两个整型日期之间的天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Integer daysBetweenDate(Integer startDate, Integer endDate) {
		if (startDate == null || endDate == null) {
			return null;
		}
		Calendar c1 = newCalendar(startDate);
		Calendar c2 = newCalendar(endDate);

		Long lg = (c2.getTimeInMillis() - c1.getTimeInMillis()) / 1000 / 60 / 60 / 24;
		return lg.intValue();
	}

	/**
	 * 判断时间是否在时间段内 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @param 开始时间start
	 * @param 结束时间end
	 * @return
	 */
	public static boolean isBetween(Date date, Date start, Date end) {
		if (date.getTime() >= start.getTime() && date.getTime() <= end.getTime()) {
			return true;
		}
		return false;

	}

	/**
	 * 计算两个整型日期之间的天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Integer daysBetweenDate(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			return null;
		}
		Long interval = endDate.getTime() - startDate.getTime();
		interval = interval / (24 * 60 * 60 * 1000);
		return interval.intValue();
	}

	/**
	 * 计算两个日期之间的月份
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Integer betweenMonths(Date startDate, Date endDate) {
		int months = 0;// 相差月份
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		int startYear = start.get(Calendar.YEAR);
		int startMonth = start.get(Calendar.MONTH);

		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		int endYear = end.get(Calendar.YEAR);
		int endMonth = end.get(Calendar.MONTH);

		if (startDate.getTime() < endDate.getTime()) {
			months = endMonth - startMonth + (endYear - startYear) * 12;
		} else {
			months = startMonth - endMonth + (startYear - endYear) * 12;
		}
		return months;
	}

	/**
	 * 取得当前日期.
	 * 
	 * @return 当前日期,字符串类型.
	 */
	public static String getDateString() {
		return formatDate(new Date());
	}

	/**
	 * 根据calendar产生字符串型日期
	 * 
	 * @param d
	 * @return eg:20080707
	 */
	public static String getStringDate(Date d) {
		if (d == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(d);
	}

	public static String getFormatStringDate(Date d) {
		if (d == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		return sdf.format(d);
	}

	public static Date convertToDate(Object obj) {
		Date date = null;
		try {
			if (obj == null) {
				date = new Date();
			} else if (obj.getClass() == Timestamp.class) {
				date = new Date(((Timestamp) obj).getTime());
			} else if (obj.getClass() == java.sql.Date.class) {
				date = new Date(((java.sql.Date) obj).getTime());
			} else if (obj.getClass() == Date.class) {
				date = (Date) obj;
			} else if (obj.getClass() == String.class) {
				for (int i = 0; i < regDate.length; i++) {
					if (date != null) {
						break;
					}
					try {
						date = convertToDate(obj, regDate[i]);
					} catch (Exception e) {
						continue;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date convertToDate(Object obj, String regula) {
		SimpleDateFormat df2 = new SimpleDateFormat(regula);
		Date date = null;
		try {
			if (obj == null) {
				date = new Date();
			} else if (obj.getClass() == String.class) {
				date = df2.parse((String) obj);
			} else if (obj.getClass() == Timestamp.class) {
				date = new Date(((Timestamp) obj).getTime());
			} else if (obj.getClass() == java.sql.Date.class) {
				date = new Date(((java.sql.Date) obj).getTime());
			} else if (obj.getClass() == Date.class) {
				date = (Date) obj;
			} else {
				date = new Date();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date convertString(String value, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (StringUtils.isEmpty(value))
			return null;
		try {
			return sdf.parse(value);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 取得多少天后的数据。
	 * 
	 * @param days
	 * @return
	 */
	public static long getNextDays(int days) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		String str = String.valueOf(cal.getTimeInMillis());
		return Long.parseLong((str.substring(0, str.length() - 3) + "000"));
	}

	/**
	 * 取得下一天。
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date getNextDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	/*
	 * 取得下一秒。
	 * 
	 * @param date
	 * 
	 * @param days
	 * 
	 * @return
	 */
	public static Date getNextSecond(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, 1);
		return cal.getTime();
	}

	public static Date parse(String dateValue, String pattern) {
		if (StringUtils.isEmpty(dateValue)) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		try {
			return dateFormat.parse(dateValue);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		return null;
	}

	public static Date parse(String dateValue) {
		return parse(dateValue, DATE_FORMAT);
	}

	public static Date addDate(String date) {
		if (date == null) {
			return null;
		}
		Date tmpDate = parse(date, "yyyy-MM-dd");

		Calendar cal = Calendar.getInstance();
		cal.setTime(tmpDate);
		cal.add(5, 1);

		return cal.getTime();
	}

	/**
	 * Return the current date
	 * 
	 */
	public static Date now() {
		Calendar cal = Calendar.getInstance();
		Date currDate = cal.getTime();

		return currDate;
	}

	public static Timestamp nowTimestamp() {
		Calendar cal = Calendar.getInstance();
		return new Timestamp(cal.getTimeInMillis());
	}

	/**
	 * Return the current date string
	 * 
	 */
	public static String nowString() {
		Calendar cal = Calendar.getInstance();
		Date currDate = cal.getTime();

		return formatDate(currDate);
	}

	/**
	 * Return the current date in the specified format
	 * 
	 * @param strFormat
	 * @return
	 */
	public static String nowString(String pattern) {
		Calendar cal = Calendar.getInstance();
		Date currDate = cal.getTime();

		return format(currDate, pattern);
	}

	public static String format(Date d, String pattern) {
		if (d == null)
			return null;

		SimpleDateFormat dateFromat = new SimpleDateFormat(pattern);
		return dateFromat.format(d);
	}

	/**
	 * Parse a strign and return a datetime value
	 * 
	 * @param dateValue
	 * @return
	 */
	public static Date parseTime(String dateValue) {
		return parse(dateValue, TIME_PATTERN_DEFAULT);
	}

	/**
	 * 获取当月的 天数
	 */
	public static int getCurrentMonthDay() {

		Calendar a = Calendar.getInstance();
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 根据年 月 获取对应的月份 天数
	 */
	public static int getDaysByYearMonth(int year, int month) {

		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 根据日期 找到对应月的 天数
	 * 
	 * @throws ParseException
	 */
	public static int getDaysByDate(String date) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try {
			Date myDate = myFormatter.parse(date);
			c.setTime(myDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;

		return getDaysByYearMonth(year, month);
	}

	/**
	 * 根据日期 找到对应日期的 星期
	 */

	public static String getDayOfWeekByDate(Date date) {
		String str = "";
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		str = c.get(Calendar.DAY_OF_WEEK) + "";
		if ("1".equals(str)) {
			str = "星期日";
		} else if ("2".equals(str)) {
			str = "星期一";
		} else if ("3".equals(str)) {
			str = "星期二";
		} else if ("4".equals(str)) {
			str = "星期三";
		} else if ("5".equals(str)) {
			str = "星期四";
		} else if ("6".equals(str)) {
			str = "星期五";
		} else if ("7".equals(str)) {
			str = "星期六";
		}
		return str;
	}

	/**
	 * 取得Integer型的当前年份
	 * 
	 * @return
	 */
	public static Integer getYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		return year;
	}

	/**
	 * 取得Integer型的当前月份
	 * 
	 * @return
	 */
	public static Integer getMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH) + 1;
		return month;
	}

	/**
	 * 取得Integer型的当前月份
	 * 
	 * @return
	 */
	public static Integer getDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.DATE);
		return month;
	}

	/**
	 * 获得日期字符串数组
	 * 
	 */

	public static Date[] getDateArrays(Date start, Date end) {
		ArrayList<Date> ret = new ArrayList<Date>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(start);
		Date tmpDate = calendar.getTime();
		long endTime = end.getTime();
		while (tmpDate.before(end) || tmpDate.getTime() == endTime) {
			ret.add(calendar.getTime());
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			tmpDate = calendar.getTime();
		}

		Date[] dates = new Date[ret.size()];
		return ret.toArray(dates);
	}

	/**
	 * 取得指定月份的第一天
	 * 
	 * @param strdate
	 *            String
	 * @return String
	 */

	public static String getMonthBegin(String strdate) {
		java.util.Date date = parse(strdate);
		return formatDate(date, "yyyy-MM") + "-01";
	}

	/**
	 * 取得指定月份的最后一天
	 * 
	 * @param strdate
	 *            String
	 * @return String
	 */

	public static String getMonthEnd(String strdate) {
		java.util.Date date = parse(getMonthBegin(strdate));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);

		return formatDate(calendar.getTime());
	}

	/**
	 * 
	 * 判断当前日期是星期几
	 * 
	 * @return dayForWeek 判断结果
	 * @Exception 发生异常
	 */

	public static int dayForWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}

	/**
	 * 取下一个月
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNextMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		return cal.getTime();
	}

	/**
	 * 取上一个月
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastMonth(Date date) {
		if (date == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		return cal.getTime();
	}

	/**
	 * 对两个日期进行比较，如日期1小于日期2则返回-1，相等则返回0，大于则返回1
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compareDate(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return -1;

		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();

		cal1.setTime(date1);
		cal2.setTime(date2);

		return cal1.compareTo(cal2);
	}

	/**
	 * 日期加分钟 得到新时间
	 * 
	 * <pre>
	 * 这样你就可以对当前时间进行年份的加减，比如求一年后i=1，取1年前i=-1
	 * 如果是月份加减cal.add(2, i);
	 * 如果是星期加减cal.add(3, i);
	 * 如果是每日加减cal.add(5, i);
	 * 如果是小时加减cal.add(10, i);
	 * 如果是分钟加减cal.add(12, i);
	 * 如果是秒的加减cal.add(13, i);
	 * </pre>
	 * 
	 * @param date
	 * @param i
	 * @return
	 */
	public static Date addMinutes(Date date, int i) {
		Date rtn = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		//
		cal.add(Calendar.MINUTE, i);
		rtn = cal.getTime();
		return rtn;
	}

	public static Date addDays(Date date, int i) {
		Date rtn = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		//
		cal.add(5, i);
		rtn = cal.getTime();
		return rtn;
	}

	public static Date addWeeks(Date date, int i) {
		Date rtn = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		//
		cal.add(3, i);
		rtn = cal.getTime();
		return rtn;
	}

	public static Date addMonths(Date date, int i) {
		Date rtn = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		//
		cal.add(2, i);
		rtn = cal.getTime();
		return rtn;
	}

	public static Date addYears(Date date, int i) {
		Date rtn = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		//
		cal.add(Calendar.YEAR, i);
		rtn = cal.getTime();
		return rtn;
	}

	/**
	 * 两个时间相差距离多少天多少小时多少分多少秒
	 * 
	 * <pre>
	 * &#64;param one 时间参数 1 格式：1990-01-01 12:00:00
	 * &#64;param two  时间参数 2 格式：2009-01-01 12:00:00
	 * &#64;return long[] 返回值为：[0]为 天，[1]为 小时，[2]为分钟，[3]为秒，
	 * </pre>
	 */
	public static long[] betweenTime(Date one, Date two) {
		// 毫秒ms
		long time1 = one.getTime();
		long time2 = two.getTime();
		long diff;
		if (time1 < time2) {
			diff = time2 - time1;
		} else {
			diff = time1 - time2;
		}
		long diffSeconds = diff / 1000;
		long diffMinutes = diff / (60 * 1000);
		long diffHours = diff / (60 * 60 * 1000);
		long diffDays = diff / (24 * 60 * 60 * 1000);

		long[] times = { diffDays, diffHours, diffMinutes, diffSeconds, diff };
		return times;
	}

	// 获取昨天日期
	public static String getYesterday() {
		Calendar cal = Calendar.getInstance();
		cal.add(5, -1);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
		return yesterday;
	}

	// 获取昨天日期
	public static String getTomorrow() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, +1);
		String tomorrow = new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
		return tomorrow;
	}

	// 获取本月最后一天日期
	public static String getDefaultDay() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(5, 1);
		lastDate.add(2, 1);
		lastDate.add(5, -1);
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获取上月第一天日期
	public static String getPreviousMonthFirst() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(5, 1);
		lastDate.add(2, -1);
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获取本月第一天日期
	public static String getFirstDayOfMonth() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(5, 1);
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获取本周日的日期
	public static String getCurrentWeekday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(5, mondayPlus + 6);
		Date monday = currentDate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = sdf.format(monday);
		return preMonday;
	}

	// 获取当天日期
	public static String getNowTime(String dateformat) {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);
		String hehe = dateFormat.format(now);
		return hehe;
	}

	private static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		int dayOfWeek = cd.get(7) - 1;
		if (dayOfWeek == 1) {
			return 0;
		}
		return (1 - dayOfWeek);
	}

	// 获取本周一日期
	public static String getMondayOFWeek() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(5, mondayPlus);
		Date monday = currentDate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = sdf.format(monday);
		return preMonday;
	}

	// 获取上周日日期
	public static String getPreviousWeekSunday() {
		int weeks = -1;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(5, mondayPlus + weeks);
		Date monday = currentDate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = sdf.format(monday);
		return preMonday;
	}

	// 获取上周一日期
	public static String getPreviousWeekday() {
		int weeks = -1;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(5, mondayPlus + 7 * weeks);
		Date monday = currentDate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = sdf.format(monday);
		return preMonday;
	}

	// 获取上月最后一天的日期
	public static String getPreviousMonthEnd() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(2, -1);
		lastDate.set(5, 1);
		lastDate.roll(5, -1);
		str = sdf.format(lastDate.getTime());
		return str;
	}

	/**
	 * 计算两个日期中的日期
	 * 
	 * @param date1
	 * @param date2
	 * @return List<Date>
	 */

	public static List<Date> betweenDate(String date1, String date2) {
		List<Date> list = new ArrayList<Date>();
		list.add(parse(date1));
		if (date1.equals(date2))// 两个日期相等!
		{
			return list;
		}

		String tmp;
		if (date1.compareTo(date2) > 0) { // 确保 date1的日期不晚于date2
			tmp = date1;
			date1 = date2;
			date2 = tmp;
		}

		tmp = new SimpleDateFormat("yyyy-MM-dd").format(str2Date(date1).getTime() + 3600 * 24 * 1000);

		while (tmp.compareTo(date2) <= 0) {
			list.add(parse(tmp));
			tmp = new SimpleDateFormat("yyyy-MM-dd").format(str2Date(tmp).getTime() + 3600 * 24 * 1000);
		}
		return list;
	}

	private static Date str2Date(String str) {
		try {
			if (str == null) {
				return null;
			}

			return new SimpleDateFormat("yyyy-MM-dd").parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获得两个日期之间的所有月份
	 * 
	 * @param date1
	 * @param date2
	 * @return List<String>
	 */

	public static List<String> getMonthBetween(String minDate, String maxDate) {
		ArrayList<String> result = new ArrayList<String>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");// 格式化为年月

			Calendar min = Calendar.getInstance();
			Calendar max = Calendar.getInstance();

			min.setTime(sdf.parse(minDate));
			min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

			max.setTime(sdf.parse(maxDate));
			max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

			Calendar curr = min;
			while (curr.before(max)) {
				result.add(sdf.format(curr.getTime()));
				curr.add(Calendar.MONTH, 1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static String lastTime(Date date) {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.setTime(date);
		todayEnd.set(Calendar.HOUR_OF_DAY, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		return datetimeFormat.format(todayEnd.getTime());
	}

	public static String firstTime(Date date) {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.setTime(date);
		todayEnd.set(Calendar.HOUR_OF_DAY, 0);
		todayEnd.set(Calendar.MINUTE, 0);
		todayEnd.set(Calendar.SECOND, 0);
		return datetimeFormat.format(todayEnd.getTime());
	}

	/**
	 * 获得两个日期之间的所有月份
	 * 
	 * <pre>
	 * X < 1分钟： 刚刚
	 * 1分钟 <= X < 60分钟 ： X分钟前
	 * 60分钟 <= X < 24小时： X小时前
	 * 24小时 <= X  ： X天前
	 * 1个月 <= X   :  月前
	 * X >= 1个月          : yyyy-MM-dd
	 * </pre>
	 * 
	 * @param date1
	 * @return String
	 */
	public static String dateName(Date date) {
		long delta = new Date().getTime() - date.getTime();
		if (delta < 1L * ONE_MINUTE) {
			return ONE_SECOND_AGO;
		}
		if (delta < 45L * ONE_MINUTE) {
			long minutes = toMinutes(delta);
			return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
		}
		if (delta < 24L * ONE_HOUR) {
			long hours = toHours(delta);
			return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
		}
		if (delta < 30L * ONE_DAY) {
			long days = toDays(delta);
			return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
		} else {
			return formatDate(date, "MM-dd");
		}
	}

	private static long toSeconds(long date) {
		return date / 1000L;
	}

	private static long toMinutes(long date) {
		return toSeconds(date) / 60L;
	}

	private static long toHours(long date) {
		return toMinutes(date) / 60L;
	}

	private static long toDays(long date) {
		return toHours(date) / 24L;
	}

	/**
	 * 判断日期是否在二个日期中间
	 * 
	 * @param start
	 * @param end
	 * @param date
	 * @return
	 */
	public static Boolean betweenDate(Date start, Date end, Date date) {
		long startDate = compareDate(date, start);
		long endDate = compareDate(end, date);

		// 有无有效签到记录
		if (startDate > 0 && endDate > 0) {
			return true;
		}

		return false;
	}

	/**
	 * 计算2日期之间相差多少钱
	 * 
	 * @param one
	 * 
	 * @param two
	 *            被比较的时间 为空(null)则为当前时间
	 * 
	 * @return
	 */

	public static int betweenYear(Date one, Date two) {// 毫秒ms
		long time1 = one.getTime();
		long time2 = two.getTime();
		long diff;
		if (time1 < time2) {
			diff = time2 - time1;
		} else {
			diff = time1 - time2;
		}

		long diffDays = diff / (24 * 60 * 60 * 1000);
		return (int) diffDays / 365;
	}

	/**
	 * 获取传入月份的前一个月
	 *
	 * @return
	 */
	public static String feedate(String beFeeMonth) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		Date myDate = formatter.parse(beFeeMonth);
		Calendar c = Calendar.getInstance();
		c.setTime(myDate);
		c.add(Calendar.MONTH, -1);
		myDate = c.getTime();
		return formatter.format(myDate);
	}

	/**
	 * 获取当前年月
	 *
	 * @return
	 */
	public static String findYearMonth() {
		/**
		 * 声明一个int变量year
		 */
		int year;
		/**
		 * 声明一个int变量month
		 */
		int month;
		/**
		 * 声明一个字符串变量date
		 */
		String date;
		/**
		 * 实例化一个对象calendar
		 */
		Calendar calendar = Calendar.getInstance();
		/**
		 * 获取年份
		 */
		year = calendar.get(Calendar.YEAR);
		/**
		 * 获取月份
		 */
		month = calendar.get(Calendar.MONTH) + 1;
		/**
		 * 拼接年份和月份
		 */
		date = year + "-" + (month < 10 ? "0" + month : month);
		/**
		 * 返回当前年月
		 */
		return date;
	}

	/**
	 * 获取传入月份的前一个月
	 *
	 * @return
	 */
	public static String feedate(String beFeeMonth, int m) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		Date myDate = formatter.parse(beFeeMonth);
		Calendar c = Calendar.getInstance();
		c.setTime(myDate);
		c.add(Calendar.MONTH, m);
		myDate = c.getTime();
		return formatter.format(myDate);
	}

	/**
	 * 获取传入日期当月的第一天
	 * 
	 * @return
	 */
	public static String getFirstDay(String datadate) throws Exception {
		Date date = null;
		String day_first = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		date = format.parse(datadate);

		// 创建日历
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		day_first = format.format(calendar.getTime());
		return day_first;
	}

	/**
	 * 获取传入日期前一天日期
	 * 
	 * @return
	 */
	public static String feeday(String beFeeMonth) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date myDate = formatter.parse(beFeeMonth);
		Calendar c = Calendar.getInstance();
		c.setTime(myDate);
		c.add(Calendar.DATE, -1);
		myDate = c.getTime();
		return formatter.format(myDate);
	}

	/**
	 * 日期加减
	 * 
	 * @return
	 */
	public static String feeday(String beFeeMonth, Integer d) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date myDate = formatter.parse(beFeeMonth);
		Calendar c = Calendar.getInstance();
		c.setTime(myDate);
		c.add(Calendar.DATE, -d);
		myDate = c.getTime();
		return formatter.format(myDate);
	}

	/**
	 * 获取当前月的前月的第一天
	 * 
	 * @return
	 */
	public static String beforeTheCurrentMonth() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 获取前月的第一天
		Calendar cal_1 = Calendar.getInstance();// 获取当前日期
		cal_1.add(Calendar.MONTH, -1);
		cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String firstDay = format.format(cal_1.getTime());
		return firstDay;
	}
	/**
	 * 日期加分钟 得到新时间
	 * 
	 * @param date
	 * @param i
	 * @return
	 */
	public static String addHour(String time, int hour)
	{
		String rtn = null;
		Date date = parse("2017-01-01 " + time + ":00", TIME_PATTERN_DEFAULT);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		//
		cal.add(Calendar.HOUR_OF_DAY, hour);
		Date getTime = cal.getTime();
		rtn = format(getTime, "HH:mm");
		return rtn;
	}
	/**
	 * 测试
	 */
	public static void main(String[] args) throws Exception 
	{
		for(int i=0;i<24;i++)
		{
			System.out.println(addHour("10:41", i));
		}
	}
}
