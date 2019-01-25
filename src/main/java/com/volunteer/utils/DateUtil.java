package com.volunteer.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式的处理工具类
 * @author Lofu
 *
 */
public class DateUtil {
	/** 按xxxx-xx-xx字符串格式传入日期，不含时分秒 */
	public static Date stringToDay(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		try {
			return sdf.parse(time);
		} catch (ParseException e) {
			throw new RuntimeException("时间数据保存异常，建议重新插入");
		}
	}
	
	/** 按yyyy-MM-dd hh:mm:ss字符串格式传入日期，包含时分秒 */
	public static Date StringToHMS(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			return sdf.parse(time);
		} catch (ParseException e) {
			throw new RuntimeException("时间数据保存异常，建议重新插入");
		}
	}
	
	/** 将当前时间转化为 yyyy/MM/dd 日期格式 */
	public static Date nowToDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date now = new Date();
		try {
			return sdf.parse(sdf.format(now));
		} catch (ParseException e) {
			throw new RuntimeException("时间数据保存异常，建议重新插入");
		}
	}
	
	/** 将指定的时间转化为 yyyy/MM/dd 字符串格式 */
	public static String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(date);
	}
	
	/** 将指定的时间转化为 HH:mm:ss 字符串格式 */
	public static String dateToHMS(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(date);
	}
	
	/** 将指定的时间转化为 yyyy/MM/dd HH:mm:ss 字符串格式 */
	public static String dateToYMDHMS(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return sdf.format(date);
	}
	
	/** 获取当前时间的时分秒字符串：HH:mm:ss */
	public static String nowToStringHMS() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date now = new Date();
		return sdf.format(now);
	}
	
	/** 传入当前时间，精确到时分秒 */
	public static Date getNow() {
		return new Date();
	}
	
	
	/** 传入日期转成数字 */
	public static String dateToNum(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String number = sdf.format(date);
		return number;
	}
}
