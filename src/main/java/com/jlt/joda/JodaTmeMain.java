package com.jlt.joda;

import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class JodaTmeMain {

	public static void main(String[] args) {
		DateTime in = new DateTime();

		System.out.println(DateTime.now()); // 国际标准时间
		System.out.println(in.getYear()); // 当年
		System.out.println(in.getMonthOfYear()); // 当月
		System.out.println(in.getDayOfMonth()); // 当月第几天
		System.out.println(in.getDayOfWeek());// 本周第几天
		System.out.println(in.getDayOfYear());// 本年第几天
		System.out.println(in.getHourOfDay());// 时
		System.out.println(in.getMinuteOfHour());// 分
		System.out.println(in.getMinuteOfDay());// 当天第几分钟
		System.out.println(in.getSecondOfMinute());// 秒
		System.out.println(in.getSecondOfDay());// 当天第几秒
		System.out.println(in.getWeekOfWeekyear());// 本年第几周
		System.out.println(in.getZone());// 所在时区
		System.out.println(in.dayOfWeek().getAsText()); // 当天是星期几，例如：星期五
		System.out.println(in.yearOfEra().isLeap()); // 当你是不是闰年，返回boolean值
		System.out.println(in.dayOfMonth().getMaximumValue());// 当月day里面最大的值

		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		// 时间解析
		DateTime dateTime = DateTime.parse("2012-12-21 23:22:45", format);
		// 时间格式化，输出==> 2012/12/21 23:22:45 Fri
		String string_u = dateTime.toString("yyyy/MM/dd HH:mm:ss EE");
		System.out.println(string_u);
		// 格式化带Locale，输出==> 2012年12月21日 23:22:45 星期五
		String string_c = dateTime.toString("yyyy年MM月dd日 HH:mm:ss EE", Locale.CHINESE);
		System.out.println(string_c);
	}
}
