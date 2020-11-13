package com.redteamobile.generator.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 * @author xwq 日期工具类
 *
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
			"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss",
			"yyyy.MM.dd HH:mm", "yyyy.MM" };

	private static final Logger logger = LoggerFactory.getLogger(com.redteamobile.es.foundation.utils.DateUtils.class);

	private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
	/**
	 * @return 获取当前月的第一天 00:00:00
	 */
	public static Date getFirstDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	public static Date getFirstDayOfMonthForJasper() {
		Calendar cal = Calendar.getInstance();
		Calendar nowCalendar = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 27);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND, 0);
		if(nowCalendar.get(Calendar.DAY_OF_MONTH) < 27) {
			cal.add(Calendar.MONTH, -1);
		}
		return cal.getTime();
	}

	public static Date getLastDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, cal.getActualMaximum(Calendar.MILLISECOND));
		return cal.getTime();
	}


	public static Date getLastDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MILLISECOND, cal.getActualMaximum(Calendar.MILLISECOND));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);

		final int last = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, last);
		return cal.getTime();
	}

	public static Date getLastDayOfMonthForJasper() {
		Calendar cal = Calendar.getInstance();
		Calendar newCal = Calendar.getInstance();
		logger.info("cal:" + cal.getTime());
		logger.info("newCal:" + cal.getTime());
		cal.set(Calendar.DAY_OF_MONTH, 26);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, cal.getActualMaximum(Calendar.MILLISECOND));
		logger.info("day of month :" + newCal.get(Calendar.DAY_OF_MONTH));
		if (newCal.get(Calendar.DAY_OF_MONTH) >= 27) {
			logger.info("day of month is longer than 27");
			cal.add(Calendar.MONTH, 1);
		}
		return cal.getTime();
	}
	public static Date getLastDayOfMonthForJasperByDate(Date date) {
		Calendar cal = Calendar.getInstance();
		Calendar newCal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 26);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, cal.getActualMaximum(Calendar.MILLISECOND));
		if (newCal.get(Calendar.DAY_OF_MONTH) >= 27) {
			cal.add(Calendar.MONTH, +1);
		}
		return cal.getTime();
	}


	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	/**
	 * 得到当前日期字符串 格式（yyyyMMdd）
	 */
	public static String getDateFormatYyyymmdd() {
		return getDate("yyyyMMdd");
	}

	/**
	 * 得到当前年月份字符串 格式（yyyyMM）
	 */
	public static String getDateFormatYyyymm() {
		return getDate("yyyyMM");
	}
	/**
	 * 得到指定年月份字符串 格式（yyyyMM）
	 */
	public static String getDateFormatYyyymmByDate(Date date) {
		return DateFormatUtils.format(date, "yyyyMM");
	}

	/**
	 * 得到指定年月份字符串 格式（yyyy-MM-dd）
	 */
	public static String getDateFormatYyyymmddByDate(Date date) {
		return DateFormatUtils.format(date, "yyyy-MM-dd");
	}

	/**
	 * 得到指定年月份字符串 格式（yyyy-MM-dd）
	 */
	public static String getDateFormatYYYYmmddByDate(Date date) {
		return DateFormatUtils.format(date, "yyyyMMdd");
	}


	/**
	 * 得到指定年月份字符串 格式（yyyy-MM-dd）
	 */
	public static String getAllDateFormatYYYYmmdd(Date date) {
		return DateFormatUtils.format(date, "yyyyMMddHHmmssSSS");
	}

	/**
	 * 得到指定年月日时分字符串 格式（yyyyMMddHHmm）
	 */
	public static String getDateFormatYYYYmmddHHmm(Date date) {
		return DateFormatUtils.format(date, "yyyyMMddHHmm");
	}

	/**
	 * 得到当前年月日时分字符串 格式（yyyyMMddHHmm）
	 */
	public static String getCurrentDateFormatYYYYmmddHHmm() {
		return DateFormatUtils.format(new Date(), "yyyyMMddHHmm");
	}

	public static String getDateFormatFullByDate(Date date) {
		return DateFormatUtils.format(date, "yyyy.MM.dd HH:mm:ss");
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	/**
	 * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
	 * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy.MM.dd",
	 * "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * 
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (24 * 60 * 60 * 1000);
	}

	/**
	 * 获取过去的小时
	 * 
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 60 * 1000);
	}

	/**
	 * 获取过去的分钟
	 * 
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 1000);
	}

	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * 
	 * @param timeMillis
	 * @return
	 */
	public static String formatDateTime(long timeMillis) {
		long day = timeMillis / (24 * 60 * 60 * 1000);
		long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
		long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
		return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
	}

	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}

	/**
	 * 格式化日期
	 * 
	 * @param format
	 *            日期格式
	 * @param date
	 *            日期时间
	 * @return 格式化的字符串
	 */
	public static String formatDate(String format, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * add by zhenzhen 2016-10-13 得到本周周一
	 * 
	 * @return yyyy-MM-dd
	 */
	public static Date getMondayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 1);
		return c.getTime();// df2.format(c.getTime());
	}

	/**
	 * add by zhenzhen 2016-10-13 得到本周周日
	 * 
	 * @return yyyy-MM-dd
	 */
	public static Date getSundayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 7);
		return c.getTime();
	}

	/**
	 * @return 获取下个月的第一天 00:00:00
	 */
	public static Date getFirstDayOfNextMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		// 将小时至0
		cal.set(Calendar.HOUR_OF_DAY, 0);
		// 将分钟至0
		cal.set(Calendar.MINUTE, 0);
		// 将秒至0
		cal.set(Calendar.SECOND, 0);
		// 将毫秒至0
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.MONTH, 1);
		return cal.getTime();
	}

	public static Date getFirstDayOfLastMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		// 将小时至0
		cal.set(Calendar.HOUR_OF_DAY, 0);
		// 将分钟至0
		cal.set(Calendar.MINUTE, 0);
		// 将秒至0
		cal.set(Calendar.SECOND, 0);
		// 将毫秒至0
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.MONTH, -1);
		return cal.getTime();
	}

	/**
	 * @return 获取下个月的第一天 00:00:00
	 */
	public static Date getFirstDayOfNextMonthForJasper() {
		Calendar nowCalendar = Calendar.getInstance();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 27);
		// 将小时至0
		cal.set(Calendar.HOUR_OF_DAY, 0);
		// 将分钟至0
		cal.set(Calendar.MINUTE, 0);
		// 将秒至0
		cal.set(Calendar.SECOND, 0);
		// 将毫秒至0
		cal.set(Calendar.MILLISECOND, 0);
		if(nowCalendar.get(Calendar.DAY_OF_MONTH) > 27) {
			cal.add(Calendar.MONTH, 1);
		}

		return cal.getTime();
	}
	
	public static Date getLastDayOfNextMonth() {
		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.DAY_OF_MONTH, 1);
		// 将小时至23
		cal.set(Calendar.HOUR_OF_DAY, 23);
		// 将分钟至59
		cal.set(Calendar.MINUTE, 59);
		// 将秒至59
		cal.set(Calendar.SECOND, 59);
		// 将毫秒至999
		cal.set(Calendar.MILLISECOND, 000);
		cal.add(Calendar.MONTH, 2);  
	    cal.set(Calendar.DATE, 0);  
		return cal.getTime();
	}

	public static Date getLastDayOfBeforeMonth() {
		Calendar cal = Calendar.getInstance();
		// 将小时至23
		cal.set(Calendar.HOUR_OF_DAY, 00);
		// 将分钟至59
		cal.set(Calendar.MINUTE, 00);
		// 将秒至59
		cal.set(Calendar.SECOND, 00);
		// 将毫秒至999
		cal.set(Calendar.MILLISECOND, 000);
		cal.add(Calendar.MONTH, 0);
		cal.set(Calendar.DATE, 0);
		return cal.getTime();
	}

	public static Date getLastDayOfNextMonthForJasper() {
		Calendar nowCalendar = Calendar.getInstance();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 26);
		// 将小时至23
		cal.set(Calendar.HOUR_OF_DAY, 23);
		// 将分钟至59
		cal.set(Calendar.MINUTE, 59);
		// 将秒至59
		cal.set(Calendar.SECOND, 59);
		// 将毫秒至999
		cal.set(Calendar.MILLISECOND, 999);
		if(nowCalendar.get(Calendar.DAY_OF_MONTH) < 27) {
			cal.add(Calendar.MONTH, 1);
		}else {
			cal.add(Calendar.MONTH, 2);
		}

		return cal.getTime();
	}
	public static Date getLastDayOfLastMonthForJasper() {
		Calendar nowCalendar = Calendar.getInstance();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 26);
		// 将小时至23
		cal.set(Calendar.HOUR_OF_DAY, 23);
		// 将分钟至59
		cal.set(Calendar.MINUTE, 59);
		// 将秒至59
		cal.set(Calendar.SECOND, 59);
		// 将毫秒至999
		cal.set(Calendar.MILLISECOND, 999);

		if(nowCalendar.get(Calendar.DAY_OF_MONTH) < 27) {
			cal.add(Calendar.MONTH, -1);
		}

//		cal.set(Calendar.DATE, 0);
		return cal.getTime();
	}

	public static Date getFirstOfMonthForJasper() {
		Calendar nowCalendar = Calendar.getInstance();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 27);
		// 将小时至23
		cal.set(Calendar.HOUR_OF_DAY, 0);
		// 将分钟至59
		cal.set(Calendar.MINUTE, 00);
		// 将秒至59
		cal.set(Calendar.SECOND, 00);
		// 将毫秒至999
		cal.set(Calendar.MILLISECOND, 000);

		if(nowCalendar.get(Calendar.DAY_OF_MONTH) < 27) {
			cal.add(Calendar.MONTH, -1);
		}

//		cal.set(Calendar.DATE, 0);
		return cal.getTime();
	}



	public static String getDateYMD() {
		return getDate("yyyyMMdd");
	}
	
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -2);
		System.out.println(getFirstDayOfLastMonth());
//		System.out.println(DateUtils.getUTCTime());
//		System.out.println(getGMTTime());
	}

	public static int daysBetween(Date smdate,Date bdate) throws ParseException
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		smdate=sdf.parse(sdf.format(smdate));
		bdate=sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days=(time2-time1)/(1000*3600*24);

		return Integer.parseInt(String.valueOf(between_days));
	}

//	public static String getLocalTimeFromUTC(Date date){
//		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
//		java.util.Date UTCDate = null ;
//		String localTimeStr = null ;
//		try {
//			UTCDate = format.parse(UTCTime);
//			format.setTimeZone(TimeZone.getTimeZone("GMT-8")) ;
//			localTimeStr = format.format(UTCDate) ;
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//
//		return localTimeStr ;
//	}

	/**
	 * 获取当前时区与北京时区相差时区数
	 * @return
	 */
	public static Integer getZoneOffSetBetweenGMT() {
		Calendar cal = Calendar.getInstance();
		int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
		int dstOffset = cal.get(Calendar.DST_OFFSET);
		return ((zoneOffset + dstOffset)/1000/60/60) - 8;

	}

	public static String getUTCTime() {
		StringBuffer UTCTimeBuffer = new StringBuffer();
		// 1、取得本地时间：
		Calendar cal = Calendar.getInstance();
		// 2、取得时间偏移量：
		int zoneOffset = cal.get(Calendar.ZONE_OFFSET);

		// 3、取得夏令时差：
		int dstOffset = cal.get(Calendar.DST_OFFSET);
		// 4、从本地时间里扣除这些差量，即可以取得UTC时间：
		cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		UTCTimeBuffer.append(year).append("-").append(month).append("-").append(day) ;
		UTCTimeBuffer.append(" ").append(hour).append(":").append(minute).append(":").append(second) ;
		try{
			format.parse(UTCTimeBuffer.toString()) ;
			return UTCTimeBuffer.toString() ;
		}catch(ParseException e)
		{
			e.printStackTrace() ;
		}
		return null ;
	}

	public static String getGMTTime() {
		Date utcDate  = null;
		try {
			utcDate = format.parse(getUTCTime());
			format.setTimeZone(TimeZone.getTimeZone("GMT-8")) ;
			return format.format(utcDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}


	public static String getFormatedDateString(float timeZoneOffset){
		if (timeZoneOffset > 13 || timeZoneOffset < -12) {
			timeZoneOffset = 0;
		}

		int newTime=(int)(timeZoneOffset * 60 * 60 * 1000);
		TimeZone timeZone;
		String[] ids = TimeZone.getAvailableIDs(newTime);
		if (ids.length == 0) {
			timeZone = TimeZone.getDefault();
		} else {
			timeZone = new SimpleTimeZone(newTime, ids[0]);
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(timeZone);
		return sdf.format(new Date());
	}

	public static Date transForDateByTimestamp(long ms) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date temp = null;
		try {
			String str = sdf.format(ms);
			temp = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return temp;
	}

	public static String getDateStamp() {
		String sdf = "yyyyMMddHHmmssSSS";
		return DateFormatUtils.format(new Date(), sdf);
	}

	/**
	 * 获取当前月份天数
	 *
	 * @return
	 */
	public static int getCurrentMonthDay() {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		return a.get(Calendar.DATE);
	}
}
