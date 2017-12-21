package com.excellence.iaserver.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * 扩展的日期工具类
 * @author Huangyb
 * @date 2015-7-20
 * @2014-2015 Excellence
 */
public abstract class ExtDateUtil {
	// 默认日期格式
	private static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * XMLGregorianCalendar对象转字符串
	 * @param xmlGregorianCalendar
	 * @return
	 */
	public static String xmlGregorianCalendarToString(
			XMLGregorianCalendar xmlGregorianCalendar) {
		if(xmlGregorianCalendar==null)return "";
		return calendarToString(xmlGregorianCalendar.toGregorianCalendar());
	}
	
	/**
	 * XMLGregorianCalendar对象按照format格式转成字符串
	 * @param xmlGregorianCalendar
	 * @param format
	 * 		日期格式
	 * @return
	 */
	public static String xmlGregorianCalendarToString(
			XMLGregorianCalendar xmlGregorianCalendar,
			String format) {
		if(xmlGregorianCalendar==null)return "";
		return calendarToString(xmlGregorianCalendar.toGregorianCalendar(), format);
	}
	
	/**
	 * Calendar对象转成字符串 
	 * @param calendar
	 * @return
	 */
	public static String calendarToString(Calendar calendar){
		return dateToString(calendar.getTime());
	}
	
	/**
	 * Calendar对象按照format格式转成字符串
	 * @param calendar
	 * @param format
	 * 		日期格式
	 * @return
	 */
	public static String calendarToString(Calendar calendar, String format){
		return dateToString(calendar.getTime(), format);
	}
	
	/**
	 * Date对象转成字符串
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date){
		return dateToString(date, DEFAULT_DATE_FORMAT);
	}
	
	/**
	 * Date对象按照format格式转成字符串
	 * @param date
	 * @param format
	 * 		日期格式
	 * @return
	 */
	public static String dateToString(Date date, String format){
		if(date==null)return "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * 字符串 转 XMLGregorianCalendar对象
	 * @param str
	 * @return
	 */
	public static XMLGregorianCalendar stringToXmlGregorianCalendar(String str){
		return stringToXmlGregorianCalendar(str, DEFAULT_DATE_FORMAT);
	}
	
	/**
	 * 字符串 根据format格式转 XMLGregorianCalendar对象
	 * @param str
	 * @param format
	 * 		日期格式
	 * @return
	 */
	public static XMLGregorianCalendar stringToXmlGregorianCalendar(String str, String format){
		try {
			Calendar calendar = stringToCalendar(str, format);
			if(calendar==null)return null;
			XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory
					.newInstance().newXMLGregorianCalendar((GregorianCalendar)calendar);
			return xmlGregorianCalendar;
		} catch (DatatypeConfigurationException e) {
			return null;
		}
	}
	
	/**
	 * 字符串 转 Calendar对象
	 * @param str
	 * @return
	 */
	public static Calendar stringToCalendar(String str){
		return stringToCalendar(str, DEFAULT_DATE_FORMAT);
	}
	
	/**
	 * 字符串 根据format格式转 Calendar对象
	 * @param str
	 * @param format
	 * 		日期格式
	 * @return
	 */
	public static Calendar stringToCalendar(String str, String format){
		Calendar calendar = Calendar.getInstance();
		Date date = stringToDate(str, format);
		if(date==null)return null;
		calendar.setTime(date);
		return calendar;
	}
	
	/**
	 * 字符串 转 Date对象
	 * @param str
	 * @return
	 */
	public static Date stringToDate(String str){
		return stringToDate(str, DEFAULT_DATE_FORMAT);
	}
	
	/**
	 * 字符串 根据format格式转 Date对象
	 * @param str
	 * @param format
	 * 		日期格式
	 * @return
	 */
	public static Date stringToDate(String str, String format){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(str);
		} catch (ParseException e) {
			return null;
		}
	}
	
}
