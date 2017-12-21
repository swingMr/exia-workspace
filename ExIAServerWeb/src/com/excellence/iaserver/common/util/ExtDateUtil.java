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
 * ��չ�����ڹ�����
 * @author Huangyb
 * @date 2015-7-20
 * @2014-2015 Excellence
 */
public abstract class ExtDateUtil {
	// Ĭ�����ڸ�ʽ
	private static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * XMLGregorianCalendar����ת�ַ���
	 * @param xmlGregorianCalendar
	 * @return
	 */
	public static String xmlGregorianCalendarToString(
			XMLGregorianCalendar xmlGregorianCalendar) {
		if(xmlGregorianCalendar==null)return "";
		return calendarToString(xmlGregorianCalendar.toGregorianCalendar());
	}
	
	/**
	 * XMLGregorianCalendar������format��ʽת���ַ���
	 * @param xmlGregorianCalendar
	 * @param format
	 * 		���ڸ�ʽ
	 * @return
	 */
	public static String xmlGregorianCalendarToString(
			XMLGregorianCalendar xmlGregorianCalendar,
			String format) {
		if(xmlGregorianCalendar==null)return "";
		return calendarToString(xmlGregorianCalendar.toGregorianCalendar(), format);
	}
	
	/**
	 * Calendar����ת���ַ��� 
	 * @param calendar
	 * @return
	 */
	public static String calendarToString(Calendar calendar){
		return dateToString(calendar.getTime());
	}
	
	/**
	 * Calendar������format��ʽת���ַ���
	 * @param calendar
	 * @param format
	 * 		���ڸ�ʽ
	 * @return
	 */
	public static String calendarToString(Calendar calendar, String format){
		return dateToString(calendar.getTime(), format);
	}
	
	/**
	 * Date����ת���ַ���
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date){
		return dateToString(date, DEFAULT_DATE_FORMAT);
	}
	
	/**
	 * Date������format��ʽת���ַ���
	 * @param date
	 * @param format
	 * 		���ڸ�ʽ
	 * @return
	 */
	public static String dateToString(Date date, String format){
		if(date==null)return "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * �ַ��� ת XMLGregorianCalendar����
	 * @param str
	 * @return
	 */
	public static XMLGregorianCalendar stringToXmlGregorianCalendar(String str){
		return stringToXmlGregorianCalendar(str, DEFAULT_DATE_FORMAT);
	}
	
	/**
	 * �ַ��� ����format��ʽת XMLGregorianCalendar����
	 * @param str
	 * @param format
	 * 		���ڸ�ʽ
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
	 * �ַ��� ת Calendar����
	 * @param str
	 * @return
	 */
	public static Calendar stringToCalendar(String str){
		return stringToCalendar(str, DEFAULT_DATE_FORMAT);
	}
	
	/**
	 * �ַ��� ����format��ʽת Calendar����
	 * @param str
	 * @param format
	 * 		���ڸ�ʽ
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
	 * �ַ��� ת Date����
	 * @param str
	 * @return
	 */
	public static Date stringToDate(String str){
		return stringToDate(str, DEFAULT_DATE_FORMAT);
	}
	
	/**
	 * �ַ��� ����format��ʽת Date����
	 * @param str
	 * @param format
	 * 		���ڸ�ʽ
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
