package com.excellence.iacommon.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;




/**
 * ������
 * 
 * @author huangjinyuan
 * @created 2017-3-23
 * @copyright Excellence co.
 */
public class T {
	
	public static final long SECOND = 1000L;
	
	public static final long MINUTE = SECOND * 60L;

	/**
	 * HOUR SECOND
	 */
	public static final long HOUR = MINUTE * 60L;

	/**
	 * DAY SECOND
	 */
	public static final long DAY = 24L * HOUR;

	/**
	 * Ĭ�ϵ�����
	 */
	public static final int DEFAULTNUM = 500;
	/**
	 * �ж��ַ����Ƿ�Ϊ��
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.length() == 0);
	}

	/**
	 * �ж��ַ����Ƿ�Ϊ��
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return (str == null || str.trim().length() == 0);
	}

	/**
	 * �ж��б��Ƿ�Ϊ��
	 * 
	 * @param list
	 * @return
	 */
	public static boolean isBlank(Collection<?> list) {
		return (list == null || list.isEmpty());
	}

	/**
	 * �ж��Ƿ�Ϊ����
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (str == null || str.trim().length() == 0) {
			return false;
		}
		int offset = 0;
		if (str.startsWith("-") || str.startsWith("+")) {
			if (str.length() > 1) {
				offset = 1;
			} else {
				return false;
			}
		}
		for (int i = offset; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * �Ƿ�����
	 * @param date
	 * @return
	 */
	public static boolean isLeapYear(Date date) {
		Calendar cal = Calendar.getInstance();
		if (date != null) {
			cal.setTime(date);
		}
		int yearNow = cal.get(Calendar.YEAR);
		if (yearNow % 400 == 0 || (yearNow % 4 == 0 && yearNow % 100 != 0)) {
			return true;
		}
		return false;
	}

	/**
	 * ת��
	 * @param arg
	 * @return
	 */
	public static String toUTF8(String arg) {
		byte b[] = arg.getBytes();
		char c[] = new char[b.length];
		for (int x = 0; x < b.length; x++) {
			c[x] = (char) (b[x] & 0xff);
		}

		return new String(c);
	}

	/**
	 * תΪ�ַ���
	 * @param i
	 * @return
	 */
	public static String toString(int i) {
		return Integer.valueOf(i).toString();
	}

	/**
	 * תΪ�ַ���
	 * @param l
	 * @return
	 */
	public static String toString(long l) {
		return Long.valueOf(l).toString();
	}

	/**
	 *  JS��ESCAPE, JAVA��
	 * @param src
	 * @return
	 */
	public static String escape(String src) {
		if (isEmpty(src)) {
			return "";
		}
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j)) {
				tmp.append(j);
			} else if (j < 256) {
				tmp.append("%");
				if (j < 16) {
					tmp.append("0");
				}
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	/**
	 * @param src
	 * @return
	 */
	public static String unescape(String src) {
		if (isEmpty(src)) {
			return "";
		}
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}


	/**
	 *  max �Ǹ����Ļ���ȫ���滻
	 * @param text
	 * @param repl
	 * @param with
	 * @param max
	 * @return
	 */
	public static String replace(String text, String repl, String with, int max) {
		if (text == null || repl == null || with == null || repl.length() == 0 || max == 0) {
			return text;
		}
		StringBuffer buf = new StringBuffer(text.length());
		int start = 0, end = 0;
		while ((end = text.indexOf(repl, start)) != -1) {
			buf.append(text.substring(start, end)).append(with);
			start = end + repl.length();
			if (--max == 0) {
				break;
			}
		}
		buf.append(text.substring(start));
		return buf.toString();
	}

	/**
	 * ȫ���滻
	 * @param text
	 * @param repl
	 * @param with
	 * @return
	 */
	public static String replace(String text, String repl, String with) {
		return replace(text, repl, with, -1);
	}

	/**
	 *  finalLength ���滻���ܵ��ַ�������.
	 * @param source
	 * @param ab
	 * @param replaceShowCount
	 * @param finalLength
	 * @return
	 */
	public static String getAB(String source, String ab, int replaceShowCount, int finalLength) {
		if (isEmpty(source)) {
			return source;
		}
		if (source.length() <= finalLength) {
			return source;
		}
		if (ab == null) {
			ab = ".";
		}
		StringBuffer sb = new StringBuffer();

		// �Ѿ���str.length > num;
		int length = ab.length() * replaceShowCount;
		int position = finalLength;
		if (finalLength - length < finalLength) {
			position = finalLength - length;
		}
		if (position < 0) {
			while (position < 0 && replaceShowCount > 0) {
				position += ab.length();
				replaceShowCount--;
			}
		}
		// ֤��numһ��ʼ��Ϊ<0
		if (position < 0) {
			return source;
		}
		sb.append(source.substring(0, position));

		for (int i = 0; i < replaceShowCount; i++) {
			sb.append(ab);
		}
		return sb.toString();
	}

	/**
	 * ��ȡʵ��ֵ
	 * @param source
	 * @param replaceShowCount
	 * @param finalLength
	 * @return
	 */
	public static String getAB(String source, int replaceShowCount, int finalLength) {
		return getAB(source, ".", replaceShowCount, finalLength);
	}

	/**
	 * ��ȡʵ��ֵ
	 * @param source
	 * @param finalLength
	 * @return
	 */
	public static String getAB(String source, int finalLength) {
		return getAB(source, ".", 3, finalLength);
	}

	/**********************ȡĬ��valueֵ�Ŀ�ʼ******************/
	public static String stringValue(String v, String def) {
		if (v == null || v.length() == 0) {
			return def;
		}
		return v.trim();
	}

	public static String[] stringArrayValue(String[] v, String[] def) {
		if (v == null || v.length == 0) {
			return def;
		}
		return v;
	}

	public static byte byteValue(String v, byte def) {
		if (v == null || v.length() == 0) {
			return def;
		}
		try {
			return Byte.parseByte(v);
		} catch (Exception e) {
			return def;
		}
	}

	public static char charValue(String v, char def) {
		if (v == null || v.length() == 0) {
			return def;
		}
		try {
			return (char) Integer.parseInt(v);
		} catch (Exception e) {
			return def;
		}
	}

	public static int intValue(String v, int def) {
		if (v == null || v.length() == 0) {
			return def;
		}
		try {
			return Integer.parseInt(v.trim());
		} catch (Exception e) {
			return def;
		}
	}

	public static Integer integerValue(String v) {
		return integerValue(v, null);
	}

	public static Integer integerValue(String v, int def) {
		if (isBlank(v)) {
			return new Integer(def);
		}
		try {
			return Integer.valueOf(v);
		} catch (Exception e) {
			return new Integer(def);
		}
	}

	public static Integer integerValue(String v, Integer def) {
		if (isBlank(v)) {
			return def;
		}
		try {
			return Integer.valueOf(v);
		} catch (Exception e) {
			return def;
		}
	}

	public static long longValue(String v, long def) {
		if (v == null || v.length() == 0) {
			return def;
		}
		try {
			return Long.parseLong(v.trim());
		} catch (Exception e) {
			return def;
		}
	}

	public static long[] longValue(String[] v, long def) {
		if (v == null || v.length == 0) {
			return new long[0];
		}
		try {
			long[] lv = new long[v.length];
			for (int i = 0, s = v.length; i < s; i++) {
				lv[i] = T.longValue(v[i], def);
			}
			return lv;
		} catch (Exception e) {
			return new long[0];
		}
	}

	public static long[] longValue(String[] v, long[] def) {
		if (v == null || v.length == 0) {
			return def;
		}
		try {
			long[] lv = new long[v.length];
			for (int i = 0, s = v.length; i < s; i++) {
				lv[i] = (Long.parseLong(v[i].trim()));
			}
			return lv;
		} catch (Exception e) {
			return def;
		}
	}

	public static boolean booleanValue(String v, boolean def) {
		if (v == null || v.length() == 0) {
			return def;
		}

		if (v.equalsIgnoreCase("true") || v.equalsIgnoreCase("yes") || v.equalsIgnoreCase("1")) {
			return true;
		} else if (v.equalsIgnoreCase("false") || v.equalsIgnoreCase("no") || v.equalsIgnoreCase("0")) {
			return false;
		} else {
			return def;
		}
	}

	public static float floatValue(String v, float def) {
		if (v == null || v.length() == 0) {
			return def;
		}
		try {
			return Float.parseFloat(v.trim());
		} catch (Exception e) {
			return def;
		}
	}

	public static float floatValue(String v, int remain, float def) {
		try {
			BigDecimal bd = new BigDecimal(v);
			bd = bd.setScale(remain, BigDecimal.ROUND_HALF_UP);
			return bd.floatValue();
		} catch (Exception e) {
			return def;
		}
	}

	public static double doubleValue(String v, double def) {
		if (v == null || v.length() == 0) {
			return def;
		}
		try {
			return Double.parseDouble(v.trim());
		} catch (Exception e) {
			return def;
		}
	}

	public static Date dateValue(String v, String fm, Date def) {
		if (v == null || v.length() == 0) {
			return def;
		}
		try {
			return new SimpleDateFormat(fm).parse(v.trim());
		} catch (Exception e) {
			return def;
		}
	}

	public static Date dateValue(String v, Date def) {
		return dateValue(v, "yyyy-MM-dd", def);
	}

	public static Date dateValue3(String v, Date def) {
		Date dateInfo = T.dateValue(v, null);// ����ת�����ܵ����ڸ�ʽ
		if (dateInfo == null) {
			dateInfo = T.datetimeValue(v, null);
		}
		if (dateInfo == null && T.isNumeric(v)) {
			dateInfo = new Date(T.longValue(v, 0));
		}

		return dateInfo == null ? def : dateInfo;
	}

	public static Date datetimeValue(String v, Date def) {
		return dateValue(v, "yyyy-MM-dd HH:mm:ss", def);
	}

	/**
	 *  �Զ��ж�ʱ���ʽ
	 *  timeStart=true,��ȷ��ʱ��Ŀ�ʼ��timeStart=false��ȷ��ʱ��Ľ���������û��ָ��ʱ��������
	 * @param v
	 * @param def
	 * @param timeStart
	 * @return
	 */
	public static Date dateValue2(String v, Date def, boolean timeStart) {
		if (v == null || v.length() == 0) {
			return def;
		}
		if (v.matches("\\d+-\\d+-\\d+ \\d+:\\d+")) {
			if (timeStart) {
				v += ":00";
			} else {
				v += ":59";
			}
			// System.out.println("-----2-"+v);
		} else if (v.matches("\\d+-\\d+-\\d+ \\d+")) {
			if (timeStart) {
				v += ":00:00";
			} else {
				v += ":59:59";
			}
		} else if (v.matches("\\d+-\\d+-\\d+")) {
			if (timeStart) {
				v += " 00:00:00";
			} else {
				v += " 23:59:59";
			}
		}
		return dateValue(v, "yyyy-MM-dd HH:mm:ss", def);
	}

	public static long periodValue(String value, long deflt) {
		if (value == null) {
			return deflt;
		}
		long period = 0;
		long sign = 1;// ������ʶ
		int i = 0;// ��ʵλ��
		int length = value.length();
		if (length > 0 && value.charAt(i) == '-') {
			sign = -1;
			i++;
		}
		while (i < length) {
			long delta = 0;
			char ch;

			for (; i < length && (ch = value.charAt(i)) >= '0' && ch <= '9'; i++) {
				delta = 10 * delta + ch - '0';
			}

			if (i >= length) {
				// ������
				period += 1000 * delta;	
			} else {
				switch (value.charAt(i++)) {
					case 's':
					case 'S':
						period += 1000 * delta;
						break;
					case 'm':
						period += 60 * 1000 * delta;
						break;
					case 'h':
					case 'H':
						period += 60L * 60 * 1000 * delta;
						break;
					case 'd':
					case 'D':
						period += DAY * delta;
						break;
					case 'w':
					case 'W':
						period += 7L * DAY * delta;
						break;
					default:
						return deflt;
				}
			}
		}
		return sign * period;
	}
	/*************************ȡĬ��valueֵ�Ľ���******************/
	
	/*****ʱ����غ�����ʼ******/
	/**
	 * ��ȡ��ǰʱ��
	 * @return
	 */
	public static Date getNow() {
		return new Date(System.currentTimeMillis());
	}
	
	/**
	 * ���������ַ���
	 * @param dateStr
	 * @param fmt
	 * @return
	 */
	public static Date parse(String dateStr, String fmt) {
		try {
			if (dateStr == null || dateStr.equals("")) {
				return null;
			}
			DateFormat formatter = new SimpleDateFormat(fmt);
			return formatter.parse(dateStr);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}


	/**
	 *  �õ������賿��ʱ��.
	 * @return
	 */
	public static Date getToday() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 *  �õ�����23��59 ʱ��
	 * @return
	 */
	public static Date getTodayLast() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	/**
	 *  �õ�ĳһ����賿ʱ��
	 * @param date
	 * @return
	 */
	public static Date getToday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 *  �õ�ĳһ���23��59 ʱ��
	 * @param date
	 * @return
	 */
	public static Date getTodayLast(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	/**
	 * �õ�����ǰ����ʱ��
	 * @param date
	 * @param dayNum
	 * @return
	 */
	public static Date getSomeDate(Date date, int dayNum) {
		Calendar cal = Calendar.getInstance();
		long DAY = 1000 * 3600 * 24;
		cal.setTimeInMillis(date.getTime() + DAY * (long) dayNum);
		return cal.getTime();
	}

	/**
	 * �õ�����ǰ����ʱ��
	 * @param date
	 * @param monthNum
	 * @return
	 */
	public static Date getSomeMonthDate(Date date, int monthNum) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + monthNum);
		return cal.getTime();
	}

	/** �õ�24Сʱ��ĳСʱ�Ŀ�ʼʱ��
	 * 
	 * @param date
	 * @param sub
	 * @return
	 */
	public static Date getSubsectionHourBegin(Date date, int sub) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, sub);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 *  �õ�24Сʱ��ĳСʱ��ĩβʱ��
	 * @param date
	 * @param sub
	 * @return
	 */
	public static Date getSubsectionHourEnd(Date date, int sub) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, sub);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	/**
	 *  �õ�һ������������
	 * @param date
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static List<Date> getAllDayByMonth(Date date) {
		List<Date> list = new ArrayList<Date>();
		// �õ����µĿ�ʼ
		Date d1 = getMonthStart(date);
		list.add(d1);
		// �õ����µĿ�ʼʱ��
		Date d2 = getMonthStart(getSomeMonthDate(date, 1));
		// �õ����½���ʱ��
		Date d3 = getSomeDate(d2, -1);
		Date d4 = d1;
		while (d4.getDate() != d3.getDate()) {
			// �ӱ��¿�ʼʱ�����+1 ������ʱ��
			d4 = getSomeDate(d4, 1);
			list.add(d4);
		}
		return list;
	}

	/**
	 *  �õ����¿�ʼ֮����ǰ���ڵ�ʱ��
	 * @param date
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static List<Date> getAllDayByMonthAndDay(Date date) {
		List<Date> list = new ArrayList<Date>();
		// �õ����µĿ�ʼ
		Date d1 = getMonthStart(date);
		list.add(d1);
		Date d4 = d1;
		while (d4.getDate() != date.getDate()) {
			// �ӱ��¿�ʼʱ�����+1 ������ʱ��
			d4 = getSomeDate(d4, 1);
			list.add(d4);
		}
		return list;
	}

	/**
	 * ��ȡ��������
	 * @return
	 */
	public static Date getYesterday() {
		Date today = T.getNow();
		long t = today.getTime();
		Date date = new Date(t - 24 * 60 * 60 * 1000l);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = sdf.parse(sdf.format(date));
		} catch (ParseException e) {
		}
		return date;
	}

	/**
	 * ��ȡָ����ʱ��
	 * @param date
	 * @return
	 */
	public static Date getTheDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * ��ȡָ�����ڵ����
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.YEAR);
	}

	/**
	 *  ���ص������� ��Ȼ��-1 Ҳ����˵���ص����Ǵ� 0--11
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.MONTH);
	}

	/**
	 * ��ȡָ�������ǵ�ǰ�µĵڼ���
	 * @param date
	 * @return
	 */
	public static int getDate(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * ��ȡָ�����ڵ�Сʱ��
	 * @param date
	 * @return
	 */
	public static int getHour(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * ��ȡָ�����ڵķ�����
	 * @param date
	 * @return
	 */
	public static int getMin(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.MINUTE);
	}

	/**
	 * ��ʽ������
	 * @param date
	 * @param fmt
	 * @return
	 */
	public static String format(Date date, String fmt) {
		if (date == null) {
			return "";
		}
		DateFormat formatter = new SimpleDateFormat(fmt);
		return formatter.format(date);
	}

	/**
	 * ��ʽ�����ڣ���ʽ��yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		return format(date, "yyyy-MM-dd");
	}

	/**
	 * ��ʽ����ǰ���ڣ���ʽ��yyyy-MM-dd
	 * @return
	 */
	public static String format() {
		return format(new Date(System.currentTimeMillis()), "yyyy-MM-dd");
	}

	/**
	 * ��ʽ��ʱ�䣬��ȷ���룬��ʽ��yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String formatDateTime(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * ��ʽ����ǰʱ�䣬��ȷ���룬��ʽ��yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String formatDateTime() {
		return format(new Date(System.currentTimeMillis()), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 *  �õ�������ڿ�ʼ��ʱ��,���ڵĿ�ʼ��getFirstDayOfWeek()�õ�
	 * @return
	 */
	public static Date getThisWeekStart() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -(cal.get(Calendar.DAY_OF_WEEK) - 1));
		return getTheDay(cal.getTime());
	}

	/**
	 *  ����ʱ�� yyyy-MM-dd HH:mm:ss.SSS ��Ӧ���ֶ�
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @param milliSecond
	 * @return
	 */
	public static Date createDate(int year, int month, int day, int hour, int minute, int second, int milliSecond) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		cal.set(Calendar.MILLISECOND, milliSecond);
		return cal.getTime();
	}

	/**
	 *  ���µĿ�ʼ
	 * @return
	 */
	public static Date getThisMonthStart() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return getTheDay(cal.getTime());
	}

	/**
	 *  ���µĿ�ʼ
	 * @param date
	 * @return
	 */
	public static Date getMonthStart(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return getTheDay(cal.getTime());
	}

	/**
	 * ָ���·ݵ��¿�ʼ����
	 * @param amount
	 * @return
	 */
	public static Date getTheMonthStart(int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisMonthStart());
		cal.add(Calendar.MONTH, amount);
		return cal.getTime();
	}

	/**
	 *  ���µĽ���
	 * @return
	 */
	public static Date getThisMonthEnd() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return getTodayLast(cal.getTime());
	}

	/**
	 *  ���µĽ���
	 * @param date
	 * @return
	 */
	public static Date getThisMonthEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return getTodayLast(cal.getTime());
	}

	/**
	 *  n����ǰ��� + -
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date addMinute(Date date, int minute) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minute);
		return new Date(cal.getTime().getTime());
	}

	/**
	 *  nСʱǰ��� + -
	 * @param date
	 * @param hour
	 * @return
	 */
	public static Date addHour(Date date, int hour) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, hour);
		return new Date(cal.getTime().getTime());
	}

	/**
	 *  n��ǰ��� + -
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date addDay(Date date, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, day);

		return new Date(cal.getTime().getTime());
	}

	/**
	 *  n��ǰ��� + -
	 * @param date
	 * @param month
	 * @return
	 */
	public static java.util.Date addMonth(java.util.Date date, int month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, month);

		return new java.util.Date(cal.getTime().getTime());
	}

	/**
	 *  n��ǰ��� + -
	 * @param date
	 * @param year
	 * @return
	 */
	public static java.util.Date addYear(java.util.Date date, int year) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, year);

		return new java.util.Date(cal.getTime().getTime());
	}

	/**
	 *  ������������֮�������
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getDays(Date date1, Date date2) {
		int days = 0;
		days = (int) (Math.abs((date2.getTime() - date1.getTime())) / (24 * 60 * 60 * 1000));
		return days;
	}
	
	/**
	 * ������������֮���Сʱ��
	 * @param date1
	 * @param date2
	 * @return<br>
	 * @author huangjinyuan, 2015��6��10��.<br>
	 */
	public static int getHours(Date date1, Date date2) {
		int hours = 0;
		hours = (int) (Math.abs((date2.getTime() - date1.getTime())) / (60 * 60 * 1000));
		return hours;
	}

	/**
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getDays(String date1, String date2) {
		int days = 0;
		days = (int) (Math.abs((dateValue(date1, "yyyy-Mm-dd", new Date()).getTime() - dateValue(date2, "yyyy-Mm-dd", new Date()).getTime())) / (24 * 60 * 60 * 1000));
		return days;
	}

	/**
	 *  ������������֮���ʱ��� ��ϸ���� ��������ΪString
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static String getDayDif(Date date1, Date date2) {
		long DAY = 24 * 60 * 60 * 1000;
		long between = Math.abs((date2.getTime() - date1.getTime()));
		long day = between / DAY;
		long hour = (between / (60 * 60 * 1000) - day * 24);
		long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		return "" + day + "��" + hour + "Сʱ" + min + "��" + s + "��";
	}

	/**
	 *  ʱ���ʽΪyyyy-MM-dd HH:mm:ss
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static String getDayDif(String date1, String date2) {
		long DAY = 24 * 60 * 60 * 1000;
		long between = Math.abs(dateValue(date1, "yyyy-MM-dd HH:mm:ss", new Date()).getTime() - dateValue(date2, "yyyy-MM-dd HH:mm:ss", new Date()).getTime());
		long day = between / DAY;
		long hour = (between / (60 * 60 * 1000) - day * 24);
		long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		return "" + day + "��" + hour + "Сʱ" + min + "��" + s + "��";
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static java.sql.Date d2d(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}

	/**
	 * 
	 * @param clasz
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static URL getClassURL(Class clasz) {
		String strName = clasz.getName();
		strName = strName.replace('.', '/');
		strName = "/" + strName + ".class";
		return clasz.getResource(strName);
	}

	static public boolean empty(String e) {
		return e == null || e.length() == 0;
	}

	static public String splitString(String[] str) {
		String result = "";
		if (str == null || str.length == 0) {
			return result;
		}
		for (int i = 0; i < str.length; i++) {
			result += str[i];
			if (i != (str.length - 1)) {
				result += ",";
			}
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	static public List string2List(String str, String split) {
		List list = null;
		String temp[] = string2Array(str, split);
		if (temp == null) {
			return list;
		}
		list = Arrays.asList(temp);
		return list;
	}

	static public String[] string2Array(String str, String split) {
		if (str == null || str.equals("")) {
			return null;
		}
		String temp[] = str.split(split);
		if (split.equals("")) {
			String result[] = new String[temp.length - 1];
			for (int i = 0; i < result.length; i++) {
				result[i] = temp[i + 1];
			}
			return result;
		} else {
			return temp;
		}
	}

	/**
	 * �ָ��ַ���
	 * @param str - �ַ���
	 * @param split - �ָ���
	 * @return
	 */
	public static List<String> string2ListNoBlank(String str, String split) {
		if (T.isBlank(str) || T.isBlank(split)) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		String temp[] = str.split(split);
		if (temp != null && temp.length > 0) {
			for (int i = 0; i < temp.length; i++) {
				if (!T.isBlank(temp[i])) {
					list.add(temp[i]);
				}
			}
		} else {
			list.add(str);
		}
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	static public List string2LongArray(String str, String split) {
		List list = string2List(str, split);
		if (list == null || list.isEmpty()) {
			return null;
		}

		List result = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			result.add(Long.parseLong(list.get(i).toString()));
		}
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	static public boolean equalLongArray(List first, List second) {
		if (first == null || second == null || first.size() != second.size()) {
			return false;
		}
		Map tmp = new HashMap();
		for (int i = 0; i < first.size(); i++) {
			tmp.put(first.get(i).toString(), 0);
		}
		for (int i = 0; i < second.size(); i++) {
			if (!tmp.containsKey(second.get(i).toString())) {
				return false;
			}
		}
		tmp.clear();
		return true;
	}

	static public String array2String(String str[], String split) {
		if (str == null) {
			return "";
		}
		String result = "";
		for (int i = 0; i < str.length; i++) {
			if (!result.equals("")) {
				result += split;
			}
			result += str[i];
		}

		return result;
	}

	@SuppressWarnings("rawtypes")
	static public String list2String(List list, String split) {
		if (list == null) {
			return "";
		}
		String result = "";
		for (int i = 0; i < list.size(); i++) {
			if (!result.equals("")) {
				result += split;
			}
			result += String.valueOf(list.get(i));
		}
		return result;
	}

	/**
	 *  ���Ա��������cutstring��һ���������������ģ����Ѿ�����html��ǩ
	 * @param value
	 * @param length
	 * @param suffix
	 * @return
	 * Created by Jack, 2013-8-15.
	 */
	public static String cutString(String value, int length, String suffix) {
		String strValue = value;
		boolean cut = false;
		int lengthByte = length * 2;
		if (value == null || value.equals("")) {
			return "";
		}
		try {
			// һ����������������
			String temp = "~@@@$1";
			strValue = strValue.replaceAll("<img src=http://www1.pcbaby.com.cn/sns/images/comment/face([0-9]*).gif>", temp);

			if (strValue.length() < length) {
				strValue = strValue.replaceAll("~@@@([0-9][0-9][0-9])", "<img src=http://www1.pcbaby.com.cn/sns/images/comment/face$1.gif>");
				// strValue = toHTML(strValue);
				strValue = toHTML(strValue, "<br>", true);
				return strValue;
			}
			if (strValue.length() > lengthByte) {
				strValue = strValue.substring(0, lengthByte);
				cut = true;
			}

			byte[] bytes = strValue.getBytes("GBK");
			if (bytes.length <= lengthByte) {
			} else {
				cut = true;
				byte[] newBytes = new byte[lengthByte];
				System.arraycopy(bytes, 0, newBytes, 0, lengthByte);
				strValue = new String(newBytes, "GBK");
				if (strValue.charAt(strValue.length() - 1) == (char) 65533) {
					strValue = strValue.substring(0, strValue.length() - 1);
				}
			}
			strValue = strValue.replaceAll("~@@@[0-9]{0,2}$", "");
			strValue = strValue.replaceAll("~@@@([0-9][0-9][0-9])", "<img src=http://www1.pcbaby.com.cn/sns/images/comment/face$1.gif>");
			// strValue = toHTML(strValue);
			strValue = toHTML(strValue, "<br>", true);

		} catch (Exception ex) {
			// System.out.println("��ȡ�ַ�������"+ex);
			return "";
		}
		if (!cut) {
			suffix = "";
		}
		return strValue + suffix;
	}

	// ȥ��html��ǩ�Ϳո�ֻ�����ı�����
	public static String justContent(String strValue, int length, String suffix) {
		if (strValue == null || strValue.equals("")) {
			return "";
		}
		// ȥ��img��ǩ�������">"����
		strValue = strValue.replaceAll("width>screen", "");
		Pattern p = null;
		Matcher m = null;
		String pattern = "<[^<>]*>?";
		p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		m = p.matcher(strValue);
		strValue = m.replaceAll("");
		strValue = strValue.replaceAll("(\\s|��|&nbsp;)*", "");

		boolean cut = false;
		int lengthByte = length * 2;
		try {
			// һ����������������
			// String temp = "~$1";
			// strValue=strValue.replaceAll("<img src=\\\"http://www1.pckids.com.cn/2008/mykids/blog/images/face/f([0-9]*).gif\\\"/>",
			// temp);

			if (strValue.length() < length) {
				// strValue =
				// strValue.replaceAll("~([0-9][0-9][0-9])",
				// "<img src=\"http://www1.pckids.com.cn/2008/mykids/blog/images/face/f$1.gif\"/>");
				strValue = startPreChange(strValue);
				strValue = toHTML(strValue, "", false);
				strValue = endPreChange(strValue);
				return strValue;
			}
			if (strValue.length() > lengthByte) {
				strValue = strValue.substring(0, lengthByte);
				cut = true;
			}

			byte[] bytes = strValue.getBytes("GBK");
			if (bytes.length <= lengthByte) {
			} else {
				cut = true;
				byte[] newBytes = new byte[lengthByte];
				System.arraycopy(bytes, 0, newBytes, 0, lengthByte);
				strValue = new String(newBytes, "GBK");
				if (strValue.charAt(strValue.length() - 1) == (char) 65533) {
					strValue = strValue.substring(0, strValue.length() - 1);
				}
			}
			// strValue =
			// strValue.replaceAll("~[0-9]{0,2}$", "");
			// strValue =
			// strValue.replaceAll("~([0-9][0-9][0-9])",
			// "<img src=\"http://www1.pckids.com.cn/2008/mykids/blog/images/face/f$1.gif\"/>");
			strValue = startPreChange(strValue);
			strValue = toHTML(strValue, "", false);
			strValue = endPreChange(strValue);

		} catch (Exception ex) {
			// System.out.println("justContent����"+ex);
			return "";
		}
		if (!cut) {
			suffix = "";
		}
		return strValue + suffix;
	}

	// ����ʵ�����ݵĳ��ȣ�ȥ��html��ǩ�Գ��ȵ�Ӱ��
	public static String subContentString(String str, int length, String suffix) {
		if (str == null || str.equals("")) {
			return "";
		}
		// ��ȥ��onload�¼���Ӱ��
		str = str.replaceAll("onload=\"javascript:if\\(this\\.width>screen\\.width-600\\)this\\.width=screen\\.width-600;\"", "");
		int cnt = 0;
		int cntTemp = 0;
		int lengthByte = length * 2;
		Matcher m = null;
		Pattern p = Pattern.compile(">([^<>]+)<", Pattern.CASE_INSENSITIVE);
		m = p.matcher(str);
		while (m.find()) {
			String s = m.group().replaceAll(">|<", "").replaceAll("&nbsp;", " ").trim();
			if (s != null && s.length() > 0) {
				try {
					byte[] b = s.getBytes("GBK");
					cntTemp = cnt;
					cnt += b.length;
					if (cnt >= lengthByte && cntTemp < lengthByte) {
						int i = m.start();
						i += (lengthByte - cntTemp) / 2;
						String result = str.substring(0, i > str.length() ? str.length() : i);
						return result + suffix;
					}

				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					return str;
				}

			}
		}

		return str;
	}

	// ȥ��html��ǩ
	public static String trimHtmlTag(String strValue) {
		if (strValue == null || strValue.equals("")) {
			return "";
		}
		// ȥ��img��ǩ�������">"����
		strValue = strValue.replaceAll("width>screen", "");
		Pattern p = null;
		Matcher m = null;
		String pattern = "<[^<>]*>?";
		p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		m = p.matcher(strValue);
		strValue = m.replaceAll("");
		return strValue;
	}

	// ��ȡ���ĳ��ȣ�������html��ǩ�������ռ�
	@SuppressWarnings("unused")
	public static String cutStringWithoutFilter(String value, int length, String suffix) {
		if(T.isBlank(value)) {
			return "";
		}
		String strValue = value.trim();
		int iLength;
		boolean cut = false;
		iLength = length;
		int lengthByte = length * 2;
		if (value == null || value.equals("")) {
			return "";
		}
		try {
			if (strValue.length() < length) {
				return strValue;
			}
			if (strValue.length() > lengthByte) {
				strValue = strValue.substring(0, lengthByte);
				cut = true;
			}

			byte[] bytes = strValue.getBytes("GBK");
			if (bytes.length <= lengthByte) {
			} else {
				cut = true;
				byte[] newBytes = new byte[lengthByte];
				System.arraycopy(bytes, 0, newBytes, 0, lengthByte);
				strValue = new String(newBytes, "GBK");
				if (strValue.charAt(strValue.length() - 1) == (char) 65533) {
					strValue = strValue.substring(0, strValue.length() - 1);
				}
			}
			strValue = reFillHtml(strValue);

		} catch (Exception ex) {
			// System.out.println("��ȡ�ַ�������"+ex);
			return "";
		}
		if (!cut) {
			suffix = "";
		}
		return strValue + suffix;
	}

	/**
	 * @param string
	 * @return
	 */
	// ���زõ���html��ǩ,���ڽ�ȡ�ռ�
	public static String reFillHtml(String string) {
		if (string == null || string.trim().equals("")) {
			return string;
		}
		// System.out.println("s----:"+string);
		// ȥ��img���Ե�>��
		string = string.replaceAll("width>screen", "width#_gt_#screen");
		// ȥ�����Ĳ������ı�ǩ
		string = string.replaceAll("<[^<>]*$", "");
		// �滻���е���ǩ
		string = string.replaceAll("<([^<>]*)/>", "#_lt_#$1/#_gt_#");
		string = string.replaceAll("<br>", "#_lt_#br#_gt_#");
		// �滻���гɶԱ�ǩ
		while (true) {
			String temp = string;
			// System.out.println("---t--:"+temp);
			string = string.replaceAll("<([^/][^<>]*)>([^<>]*)</([^<>]*)>", "#_lt_#$1#_gt_#$2#_lt_#/$3#_gt_#");
			// System.out.println("---s--:"+string);
			if (temp.equals(string)) {
				// �����쳣���������Ľ�����ǩ
				string = string.replaceAll("</[^<>]*>", "");
				break;
			}
		}
		// ���ز������ı�ǩ
		while (true) {
			String temp = string;
			string = string.replaceAll("<([^/][^\\s<>]*)([^<>]*)>([^<>]*)$", "#_lt_#$1$2#_gt_#$3#_lt_#/$1#_gt_#");
			// System.out.println("---2--:"+string);
			if (temp.equals(string)) {
				// �����쳣���������Ŀ�ʼ��ǩ
				string = string.replaceAll("<[^<>]*>", "");
				break;
			}
		}
		string = string.replaceAll("#_lt_#", "<").replaceAll("#_gt_#", ">");
		try {
			// System.out.println("bytes----:"+string.getBytes("GBK").length);
			while (string.getBytes("GBK").length >= 2000) {
				// System.out.println(string.getBytes("GBK").length+" >= 2000 ");
				string = string.replaceFirst("<([^/][^<>]*)>([^<>]*)</([^<>]*)>", "");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// System.out.println("e----:"+string);
		return string;
	}

	public static String toHTML(String str) {
		return toHTML(str, "<br>", false);
	}

	public static String toHTML(String str, String enter) {
		return toHTML(str, enter, false);
	}

	/**
	 * ��������2�����ϵ�<br> ����һ����
	 * @param str
	 * @return
	 * Created by Jack, 2013-11-28.
	 */
	public static String toBrs(String str) {
		if (str == null) {
			return str;
		}
		str = str.replaceAll("(<br>){3,}", "<br>");
		return str;
	}

	public static String toHTML(String str, String enter, boolean exceptEm) {
		if (str == null || str.equals("")) {
			return "";
		}
		// �����˱���
		if (exceptEm) {
			str = str.replaceAll("<img src=http://www1.pcbaby.com.cn/sns/images/comment/face([0-9]*).gif>", "#img$1#");
		}
		str = T.replace(str, "&", "&amp;");
		str = T.replace(str, "<", "&lt;");
		str = T.replace(str, ">", "&gt;");
		// str = T.replace(str, "  ", "&nbsp;&nbsp;");
		str = T.replace(str, " ", "&nbsp;");
		str = T.replace(str, "\r\n", "\n");
		str = T.replace(str, "\n", enter);
		str = T.replace(str, "\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		// str = T.replace(str, "'", "��");
		// str = T.replace(str, "\"", "��");
		str = T.replace(str, "'", "&#039;");
		str = T.replace(str, "\"", "&#034;");
		if (exceptEm) {
			str = str.replaceAll("#img([0-9]*)#", "<img src=http://www1.pcbaby.com.cn/sns/images/comment/face$1.gif>");
		}
		return str;
	}

	public static String startPreChange(String str) {
		str = T.replace(str, "&times;", "#times;");
		str = T.replace(str, "&hellip;", "#hellip;");
		str = T.replace(str, "&amp;", "#amp;");
		str = T.replace(str, "&lt;", "#lt;");
		str = T.replace(str, "&gt;", "#gt;");
		str = T.replace(str, "&nbsp;", "#nbsp;");
		str = T.replace(str, "<br>", "#br#");
		str = T.replace(str, "&ldquo;", "#ldquo;");// ת����˫����
		str = T.replace(str, "&rdquo;", "#rdquo;");// ת����˫����
		str = T.replace(str, "&divide;", "#divide;");// ת�����ں�
		str = T.replace(str, "&mdash;", "#mdash;");// ת�����ۺ�
		return str;
	}

	public static String endPreChange(String str) {
		str = T.replace(str, "#times;", "&times;");
		str = T.replace(str, "#hellip;", "&hellip;");
		str = T.replace(str, "#amp;", "&amp;");
		str = T.replace(str, "#lt;", "&lt;");
		str = T.replace(str, "#gt;", "&gt;");
		str = T.replace(str, "#nbsp;", "&nbsp;");
		str = T.replace(str, "#br#", "<br>");
		str = T.replace(str, "#ldquo;", "&ldquo;");
		str = T.replace(str, "#rdquo;", "&rdquo;");
		str = T.replace(str, "#divide;", "&divide;");
		str = T.replace(str, "#mdash;", "&mdash;");
		return str;
	}

	/**
	 * ת��ΪGBK��ʽ
	 * @param str
	 * @return<br>
	 * @author Jack, 2014-8-5.<br>
	 */
	public static String encode(String str) {
		return encode(str, "GBK");
	}
	
	/**
	 * ����ΪGBK��ʽ
	 * @param str
	 * @return<br>
	 */
	public static String decode(String str) {
		return decode(str, "GBK");
	}

	/**
	 * ��ָ�������ʽת��
	 * @param str
	 * @param code
	 * @return<br>
	 * @author Jack, 2014-8-5.<br>
	 */
	public static String encode(String str, String code) {
		if (str == null || str.equals("")) {
			return "";
		}
		try {
			str = java.net.URLEncoder.encode(str, code);
		} catch (UnsupportedEncodingException e) {
			//encode error, return origin.
		}
		return str;
	}

	/**
	 * ��ָ�������ʽ����
	 * @param str
	 * @param code
	 * @return<br>
	 * @author Jack, 2014-8-5.<br>
	 */
	public static String decode(String str, String code) {
		if (str == null || str.equals("")) {
			return "";
		}
		try {
			str = java.net.URLDecoder.decode(str, code);
		} catch (UnsupportedEncodingException e) {
			//decode error, return origin.
		}
		return str;
	}

	/**
	 *  �����ַ����ĳ��ȣ�һ��������2
	 * @param str
	 * @return<br>
	 * @author Jack, 2014-8-5.<br>
	 */
	public static int getLength(String str) {
		if (empty(str)) {
			return 0;
		}
		int reInt = 0;
		char[] chars = str.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			reInt += String.valueOf(chars[i]).getBytes().length;
		}
		return reInt;
	}

	/**
	 * ��URLת��Ϊ����ƴ���ύ�����е��ַ���(GBK)
	 */
	public static String encodeURL(String s) {
		return encode(s, "GBK");
	}

	/**
	 * ָ�������ʽת��
	 * @param s
	 * @param charset
	 * @return
	 * @throws Exception<br>
	 */
	public static String encodeURL(String s, String charset) {
		return encode(s, charset);
	}

	/**
	 * ���ַ�����ȡָ���ֽ������ȡ� ��Ϊjava�������ַ���ʹ��unicdoe��һ���ַ�ռ�����ֽڡ�
	 * ����ҳ����ʾ��ʱ�������ַ�ռ�Ŀ����Ӣ�ĵ�����������Ϊ�����룬 ��Ҫ�����ֽڼ�����ַ�����ȡ��
	 * �����Ҫ��ȡ�ĳ��ȣ��պð�һ�������ַ��п��ˣ���ô���ؽ���ͼ���һ���ַ�
	 * 
	 * @param str ��Ҫ������ַ���
	 * @param cutLen ��Ҫ��ȡ���ֽ���
	 * @return ������ȡ���ַ���
	 */
	public static String byteSubstring(String str, int cutLen) {
		return byteSubstring(str, cutLen, null);
	}

	/**
	 * �����ֽ�����ȡ�ַ�������ο�byteSubstring(String, int)
	 * ��ͬ���ǣ������ȡ�õ����ַ�������С��ԭ�ַ����������ʡ�Է���
	 * �������ʡ�Է�����null���߿մ���Ҳֱ�ӷ��ؽ�ȡ����ַ���
	 * 
	 * @param str ��Ҫ������ַ���
	 * @param cutLen ��Ҫ��ȡ�ĳ��ȣ��ֽ�����
	 * @param suspensionSymbol ʡ�Է���
	 * @return ������ȡ���ַ���
	 */
	public static String byteSubstring(String str, int cutLen, String suspensionSymbol) {
		if (empty(str)) {
			return str;
		}
		if (cutLen <= 0) {
			return "";
		}
		int reInt = 0;
		if (!empty(suspensionSymbol)) {
			char[] sArray = suspensionSymbol.toCharArray();
			int suspensionSymbolLength = 0;
			for (int i = 0; i < sArray.length; i++) {
				suspensionSymbolLength += String.valueOf(sArray[i]).getBytes().length;
			}
			reInt += suspensionSymbolLength;
		}
		String reStr = "";
		char[] chars = str.toCharArray();
		for (int i = 0; (i < chars.length && cutLen > reInt); i++) {
			reInt += String.valueOf(chars[i]).getBytes().length;
			reStr += chars[i];
		}
		reStr = reInt > cutLen ? reStr.substring(0, reStr.length() - 1) : reStr;
		return !empty(suspensionSymbol) && str.length() > reStr.length() ? reStr + suspensionSymbol : reStr;
	}

	/**
	 * �����ֽ�����ȡ�ַ�������ο�byteSubstring(String, int)
	 * ��ͬ���ǣ������ȡ�õ����ַ�������С��ԭ�ַ����������ʡ�Է���
	 * �������ʡ�Է�����null���߿մ���Ҳֱ�ӷ��ؽ�ȡ����ַ���
	 * 
	 * @param str ��Ҫ������ַ���
	 * @param cutLen ��Ҫ��ȡ�ĳ��ȣ��ֽ�����
	 * @param suspensionSymbol ʡ�Է���
	 * @param isFilterUBB �Ƿ���˵�UBB����
	 * @return ������ȡ���ַ���
	 */
	public static String byteSubstring(String str, int cutLen, String suspensionSymbol, boolean isFilterUBB) {
		if (isFilterUBB) {
			String s = byteSubstring(str, cutLen, suspensionSymbol);
			s = simpleStringFilter(s, "img");
			s = simpleStringFilter(s, "url");
			s = simpleStringFilter(s, "email");
			s = simpleStringFilter(s, "FLASH");
			s = simpleStringFilter(s, "RM");
			s = simpleStringFilter(s, "code");
			s = simpleStringFilter(s, "quote");
			s = simpleStringFilter(s, "color");
			s = simpleStringFilter(s, "fly");
			s = simpleStringFilter(s, "move");
			s = simpleStringFilter(s, "glow");
			s = simpleStringFilter(s, "shadow");
			s = simpleStringFilter(s, "b");
			s = simpleStringFilter(s, "i");
			s = simpleStringFilter(s, "u");
			s = simpleStringFilter(s, "center");
			s = simpleStringFilter(s, "size");
			s = simpleStringFilter(s, "face");
			s = simpleStringFilter(s, "align");
			s = simpleStringFilter(s, "em");
			s = simpleStringFilter(s, "font");
			s = simpleStringFilter(s, "WMA");
			s = simpleStringFilter(s, "WMV");
			return s;
		} else {
			return byteSubstring(str, cutLen, suspensionSymbol);
		}
	}

	/**
	 * ���˵�ĳ��UBB��tag
	 * 
	 * @param string ��Ҫ������ַ���
	 * @param filter ��Ҫ���˵�UBB��tag
	 * @return ����������ַ���
	 */
	public static String simpleStringFilter(String string, String filter) {
		if (string == null || string.trim().equals("")) {
			return string;
		}

		Pattern p = null;
		Matcher m = null;
		Pattern p1 = null;
		Matcher m1 = null;
		String pStr1 = "\\[unknown\\]";
		String pStr2 = "\\[/unknown\\]";
		if (filter.equalsIgnoreCase("url")) {
			pStr1 = "\\[url=[^\\[\\]]*\\]";
			pStr2 = "\\[/url\\]";
		} else if (filter.equalsIgnoreCase("email")) {
			pStr1 = "\\[email=[^\\[\\]]*\\]";
			pStr2 = "\\[/email\\]";
		} else if (filter.equalsIgnoreCase("color")) {
			pStr1 = "\\[\\s*color\\s*=\\s*(#?[a-z0-9].*?)\\]";
			pStr2 = "\\[\\s*/color\\s*\\]";
		} else if (filter.equalsIgnoreCase("glow")) {
			pStr1 = "\\[glow=[^\\[\\]]*\\]";
			pStr2 = "\\[/glow\\]";
		} else if (filter.equalsIgnoreCase("shadow")) {
			pStr1 = "\\[shadow=[^\\[\\]]*\\]";
			pStr2 = "\\[/shadow\\]";
		} else if (filter.equalsIgnoreCase("size")) {
			pStr1 = "\\[size=[^\\[\\]]*\\]";
			pStr2 = "\\[/size\\]";
		} else if (filter.equalsIgnoreCase("face")) {
			pStr1 = "\\[face=[^\\[\\]]*\\]";
			pStr2 = "\\[/face\\]";
		} else if (filter.equalsIgnoreCase("align")) {
			pStr1 = "\\[align=[^\\[\\]]*\\]";
			pStr2 = "\\[/align\\]";
		} else if (filter.equalsIgnoreCase("font")) {
			pStr1 = "\\[font=[^\\[\\]]*\\]";
			pStr2 = "\\[/font\\]";
		} else if (filter.equalsIgnoreCase("em")) {
			pStr1 = "\\[em([0-9]*)\\]";

		} else {
			pStr1 = pStr1.replaceAll("unknown", filter);
			pStr2 = pStr2.replaceAll("unknown", filter);
		}

		int start = 0;
		int end = 0;

		if (!filter.equals("em")) {
			p = Pattern.compile(pStr1, Pattern.CASE_INSENSITIVE);
			m = p.matcher(string);
			p1 = Pattern.compile(pStr2, Pattern.CASE_INSENSITIVE);
			m1 = p1.matcher(string);
			while (m.find()) {
				start = m.start();
				end = m.end();
				if (end < string.length()) {
					if (!m1.find(end)) {
						string = string.substring(0, start) + string.substring(end, string.length());
					}
				} else {
					if (start < string.length()) {
						string = string.substring(0, start);
					}
				}
			}
		}

		int left = string.lastIndexOf("[");
		int right = string.lastIndexOf("]");
		if (left >= 0) {
			if (left > right) {
				string = string.substring(0, left);
			}
		}
		return string;
	}

	public static boolean regulaxDomain(String domain) {
		return Pattern.matches("[a-z_0-9]{2,20}", domain);
	}

	public boolean isShow(String StringSet, int index) {
		String[] sets = string2Array(StringSet, "");
		return sets[index].equals("1") ? true : false;
	}

	/**
	 * �������������ص���������id����������Ѱ��ͬһ�����Ķ���
	 * 
	 * @param date
	 * @return
	 */
	public static int calConstellation(Date date) {
		if (date == null) {
			date = T.getNow();
		}
		SimpleDateFormat monthDayFormat = new SimpleDateFormat("MM-dd");
		String monthDay = monthDayFormat.format(date);
		if (monthDay.compareTo("03-21") >= 0 && monthDay.compareTo("04-20") < 0) {
			// System.out.println("������");
			return 1;
		}
		if (monthDay.compareTo("04-21") >= 0 && monthDay.compareTo("05-21") < 0) {
			// System.out.println("��ţ��");
			return 2;
		}
		if (monthDay.compareTo("05-22") >= 0 && monthDay.compareTo("06-21") < 0) {
			// System.out.println("˫����");
			return 3;
		}
		if (monthDay.compareTo("06-22") >= 0 && monthDay.compareTo("07-22") < 0) {
			// System.out.println("��з��");
			return 4;
		}

		if (monthDay.compareTo("07-23") >= 0 && monthDay.compareTo("08-23") < 0) {
			// System.out.println("ʨ����");
			return 5;
		}

		if (monthDay.compareTo("08-24") >= 0 && monthDay.compareTo("09-23") < 0) {
			// System.out.println("��Ů��");
			return 6;
		}

		if (monthDay.compareTo("09-24") >= 0 && monthDay.compareTo("10-23") < 0) {
			// System.out.println("�����");
			return 7;
		}

		if (monthDay.compareTo("10-24") >= 0 && monthDay.compareTo("11-22") < 0) {
			// System.out.println("��Ы��");
			return 8;
		}

		if (monthDay.compareTo("11-23") >= 0 && monthDay.compareTo("12-21") < 0) {
			// System.out.println("������");
			return 9;
		}

		if (monthDay.compareTo("12-22") >= 0 && monthDay.compareTo("12-31") <= 0 || monthDay.compareTo("01-01") >= 0 && monthDay.compareTo("01-20") < 0) {
			// System.out.println("Ħ����");
			return 10;
		}
		if (monthDay.compareTo("01-21") >= 0 && monthDay.compareTo("02-18") < 0) {
			// System.out.println("ˮƿ��");
			return 11;
		}

		if (monthDay.compareTo("02-19") >= 0 && monthDay.compareTo("03-20") < 0) {
			// System.out.println("˫����");
			return 12;
		}
		return -1;
	}

	public static String calConstellationDesc(Date date) {
		if (date == null) {
			date = T.getNow();
		}
		int num = calConstellation(date);
		switch (num) {
			case 1:
				return "������";
			case 2:
				return "��ţ��";
			case 3:
				return "˫����";
			case 4:
				return "��з��";
			case 5:
				return "ʨ����";
			case 6:
				return "��Ů��";
			case 7:
				return "�����";
			case 8:
				return "��Ы��";
			case 9:
				return "������";
			case 10:
				return "Ħ����";
			case 11:
				return "ˮƿ��";
			case 12:
				return "˫����";
			default:
				return "";
		}
	}

	public static final String[] zodiacArr = { "��", "��", "��", "��", "��", "ţ", "��", "��", "��", "��", "��", "��" };

	/**
	 * �������ڻ�ȡ��Ф
	 * 
	 * @return
	 */
	public static String date2Zodiac(Calendar time) {
		return zodiacArr[time.get(Calendar.YEAR) % 12];
	}

	public static String age(Date birthday, Date baseDate) {
		if (birthday == null) {
			return "0��0����";
		}

		Calendar now = Calendar.getInstance();
		if (baseDate != null) {
			now.setTime(T.getTheDay(baseDate));
		} else {
			now.setTime(T.getTheDay(new Date()));
		}
		Calendar birth = Calendar.getInstance();
		birth.setTimeInMillis(now.getTimeInMillis() - birthday.getTime());
		return (birth.get(Calendar.YEAR) - 1970) + "��" + (birth.get(Calendar.MONTH)) + "����";
	}

	/**
	 * ȡ��һ���������ĵ��ַ������ֽڳ���
	 */
	public static int getByteLength(String s) {
		return s == null ? 0 : s.replaceAll("[^\\x00-\\xff]", "12").length();
	}

	// ����logo��ȡ����ͼ
	public static String getLogoThumb(String logo, int size) {
		if (isEmpty(logo)) {
			return "";
		}
		Pattern jsPattern = Pattern.compile("(.*)\\.([^.]*)");
		Matcher matcher = jsPattern.matcher(logo);
		return matcher.replaceFirst("$1_thu" + size + ".$2");
	}

	public static String calTimeInterval(Date date) {
		String str = "";
		Date now = new Date();
		long interval = now.getTime() - date.getTime();
		long sec = interval / 1000;
		long min = interval / (60 * 1000);
		if (sec >= 1 && min < 1) {
			str = sec + "��ǰ";
		} else if (min >= 1 && min < 60) {// ����1���ӣ�С��1Сʱ
			str = min + "����ǰ";
		} else if (min >= 60 && min < 24 * 60) {// ����1Сʱ��С��1��
			long hour = min / 60;
			str = hour + "Сʱ" + ((min - hour * 60) > 0 ? (min - hour * 60) + "����ǰ" : "ǰ");
		} else if (min >= 24 * 60) {// ����1��
			long day = min / (24 * 60);
			str = day + "��ǰ";
		}
		return str;
	}

	public static String calTimeInterval2(Date date) {
		String str = "";
		Date now = new Date();
		long interval = now.getTime() - date.getTime();
		long sec = interval / 1000;
		long min = interval / (60 * 1000);
		if (sec >= 1 && min < 1) {
			str = sec + "��ǰ";
		} else if (min >= 1 && min < 60) {// ����1���ӣ�С��1Сʱ
			str = min + "����ǰ";
		} else if (min >= 60 && min < 24 * 60) {// ����1Сʱ��С��1��
			long hour = min / 60;
			str = hour + "Сʱ" + ((min - hour * 60) > 0 ? (min - hour * 60) + "����ǰ" : "ǰ");
		} else if (min >= 24 * 60 && min < 24 * 60 * 30 * 12) {// ����1��С��1��
			str = T.format(date, "MM-dd");
		} else {
			str = T.format(date);
		}
		return str;
	}

	public static String calTimeInterval3(Date date) {
		Calendar today = Calendar.getInstance();

		today.setTime(T.getTheDay(new Date()));
		if (date.compareTo(today.getTime()) == 1) {
			return "����";
		}
		today.add(Calendar.DAY_OF_MONTH, -1);
		if (date.compareTo(today.getTime()) == 1) {
			return "����";
		}
		today.setTime(T.getTheDay(new Date()));
		today.add(Calendar.DAY_OF_MONTH, -2);
		if (date.compareTo(today.getTime()) == 1) {
			return "ǰ��";
		}
		today.setTime(T.getTheDay(new Date()));
		today.add(Calendar.DAY_OF_MONTH, -3);
		if (date.compareTo(today.getTime()) == 1) {
			return "����ǰ";
		}
		today.setTime(T.getTheDay(new Date()));
		today.add(Calendar.DAY_OF_MONTH, -7);
		if (date.compareTo(today.getTime()) == 1) {
			return "һ��ǰ";
		}
		today.setTime(T.getTheDay(new Date()));
		today.add(Calendar.MONTH, -1);
		if (date.compareTo(today.getTime()) == 1) {
			return "һ����ǰ";
		}

		return "������ǰ";
	}

	// ��ð������ֺ���ĸ������ַ���,bitΪλ��
	public static String getRandomStr(int bit) {
		String[] str = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
				"R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
		Random rdm = new Random();
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < bit; j++) {
			sb.append(str[rdm.nextInt(36)]);
		}
		return sb.toString();
	}

	// ��ð������ֵ�����ַ���,bitΪλ��
	public static String getNumberRandomStr(int bit) {
		String[] str = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		Random rdm = new Random();
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < bit; j++) {
			sb.append(str[rdm.nextInt(10)]);
		}
		return sb.toString();
	}

	@SuppressWarnings("rawtypes")
	public static String getLocalHostIp() {
		InetAddress addr = null;
		String address = "";
		try {
			addr = InetAddress.getLocalHost();
			address = addr.getHostAddress().toString();

			if (address.indexOf("127.0.0.1") != -1) {
				Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
				InetAddress ip = null;
				while (netInterfaces.hasMoreElements()) {
					NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
					// System.out.println(ni.getName());
					ip = (InetAddress) ni.getInetAddresses().nextElement();
					if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
						address = ip.getHostAddress();
						// System.out.println("������ip=" +
						// ip.getHostAddress());
						break;
					} else {
						ip = null;
					}
				}
			}
		} catch (UnknownHostException uhe) {
		} catch (SocketException se) {
		}
		return address;// ��ñ���IP
	}

	// ð�ݷ�����
	public static void bubleSort(List<Integer> list, boolean asc) {
		if (list == null) {
			return;
		}
		int n = list.size();
		for (int i = 0; i < n; i++) {
			for (int j = n - 1; j > i; j--) {
				int int1 = list.get(j);
				int int2 = list.get(j - 1);
				if (asc) {
					if (int1 < int2) {
						list.set(j, int2);
						list.set(j - 1, int1);
					}
				} else {
					if (int1 > int2) {
						list.set(j, int2);
						list.set(j - 1, int1);
					}
				}
			}
		}
	}

	// �ٷֱ�,����,��ĸ,��λС����
	public static String percent(double numerator, double denominator, int radixPoint) {
		String str;
		double p3 = numerator / denominator;
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(radixPoint);
		str = nf.format(p3);
		return str;
	}

	// ת���ɷ���domain��ʽ���ַ���
	public static String toDomain(String domain) {
		int length = domain.length();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			char c = domain.charAt(i);
			if (Pattern.matches("[a-z_0-9]", String.valueOf(c))) {
				sb.append(c);
			}
		}
		String result = sb.toString();
		if (result.length() < 2) {
			result = getRandomStr(6).toLowerCase();
		}
		if (result.length() > 20) {
			result = result.substring(0, 20);
		}
		return result;
	}

	public static String filterInput(String html) {
		if (html == null) {
			return html;
		}
		StringBuilder sb = new StringBuilder(html.length());
		for (int i = 0, c = html.length(); i < c; ++i) {
			char ch = html.charAt(i);
			switch (ch) {
				case '>':
					sb.append("&gt;");
					break;
				case '<':
					sb.append("&lt;");
					break;
				case '&':
					sb.append("&amp;");
					break;
				case '"':
					sb.append("&quot;");
					break;
				case '\'':
					sb.append("&#039");
					break;
				default:
					sb.append(ch);
					break;
			}
		}
		return sb.toString();
	}

	/**
	 * ���б������ȡ��һ�������Ķ���
	 * 
	 * @param list
	 * @param ranCount
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List randomList(List list, int ranCount) {
		List result = new ArrayList();
		Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
		int size = list.size();
		if (size <= ranCount) {
			return list;
		}

		Random ran = new Random();
		for (int i = 0; i < ranCount; i++) {
			int index = ran.nextInt(size);
			while (indexMap.get(new Integer(index)) != null) {
				index = ran.nextInt(size);
			}
			indexMap.put(new Integer(index), new Integer(index));
		}

		for (Iterator it = indexMap.keySet().iterator(); it.hasNext();) {
			result.add(list.get((Integer) it.next()));
		}
		return result;
	}

	public static String toInput(String str) {
		if (isBlank(str)) {
			return "";
		}
		str = str.trim();
		str = T.replace(str, "<", "&lt;");
		str = T.replace(str, ">", "&gt;");
		str = T.replace(str, "&nbsp;", " ");
		str = T.replace(str, "\r\n", " ");
		str = T.replace(str, "\n", " ");
		str = T.replace(str, "\t", " ");
		str = T.replace(str, "'", "��");
		str = T.replace(str, "\"", "��");
		return str;
	}

	public static String toInput1(String str) {
		if (isBlank(str)) {
			return "";
		}
		// str = str.trim();
		str = "  " + str;
		str = T.replace(str, "<", "&lt;");
		str = T.replace(str, ">", "&gt;");
		str = T.replace(str, "&nbsp;", " ");
		str = T.replace(str, "\r\n", " ");
		str = T.replace(str, "\n", "\n  ");
		str = T.replace(str, "\t", " ");
		str = T.replace(str, "'", "��");
		str = T.replace(str, "\"", "��");
		return str;
	}

	// 1���������
	public static Date random1Minute(Date questionDate) {
		int date = 1 * 60 * 1000;
		Random random = new Random();
		return new Date(questionDate.getTime() + random.nextInt(date));
	}

	// 1-60���������
	public static Date random1T60Minute(Date questionDate) {
		Random random = new Random();
		long date = 1 + random.nextInt(59);
		return new Date(questionDate.getTime() + (date * 60 * 1000));
	}

	// X��Y���������
	public static Date randomXTYMinute(Date startDate, Date endDate) {
		long start = startDate.getTime();
		long end = endDate.getTime();
		Random random = new Random();
		long date = start + random.nextInt(new Long(end - start).intValue());
		return new Date(date);
	}

	// 10���������
	public static Date random10Minute(Date questionDate) {
		int date = 9 * 60 * 1000;
		Random random = new Random();
		return new Date(questionDate.getTime() + random.nextInt(date));
	}

	// 11-60���������
	public static Date random11T60Minute(Date questionDate) {
		Random random = new Random();
		long date = 10 + random.nextInt(49);
		return new Date(questionDate.getTime() + (date * 60 * 1000));
	}

	// 61-240���������
	public static Date random61T0240Minute(Date questionDate) {
		Random random = new Random();
		long date = 60 + random.nextInt(179);
		return new Date(questionDate.getTime() + (date * 60 * 1000));
	}

	// 2-3���������6:00-24:00��
	@SuppressWarnings("deprecation")
	public static Date random2T03Day(Date questionDate) {
		Random random = new Random();
		int day = 1 + random.nextInt(2);
		int questionHours = questionDate.getHours();
		int questionMinuteTime = (random.nextInt(60) * 60 + random.nextInt(60)) * 1000;
		int hours = 0;
		if (questionHours >= 18) {
			hours = random.nextInt(23 - questionHours);
			questionMinuteTime = questionMinuteTime * -1;
		} else {
			hours = 5 + random.nextInt(18 - questionHours);
		}
		int date = day * 24 + hours;
		return new Date(questionDate.getTime() + date * 60 * 60 * 1000 + questionMinuteTime);
	}

	// 4-7���������6:00-24:00��
	@SuppressWarnings("deprecation")
	public static Date random4T07Day(Date questionDate) {
		Random random = new Random();
		int day = 3 + random.nextInt(4);
		int questionHours = questionDate.getHours();
		int questionMinuteTime = (random.nextInt(60) * 60 + random.nextInt(60)) * 1000;
		int hours = 0;
		if (questionHours >= 18) {
			hours = random.nextInt(23 - questionHours);
			questionMinuteTime = questionMinuteTime * -1;
		} else {
			hours = 5 + random.nextInt(18 - questionHours);
		}
		int date = day * 24 + hours;
		return new Date(questionDate.getTime() + date * 60 * 60 * 1000 + questionMinuteTime);
	}

	// �����죬�Զ�ȷ�ϣ�6:00-24:00��
	@SuppressWarnings("deprecation")
	public static Date random7Day1(Date questionDate) {
		Random random = new Random();
		int questionHours = questionDate.getHours();
		int hours = 0;
		if (questionHours >= 18) {
			hours = random.nextInt(23 - questionHours);
		} else {
			hours = 5 + random.nextInt(18 - questionHours);
		}
		int date = 6 * 24 + hours;
		return new Date(questionDate.getTime() + date * 60 * 60 * 1000);
	}

	// ��Ѵ𰸣��������Ϊ��������һ��
	public static Date random7Day(Date questionDate) {
		Random random = new Random();
		int time = random.nextInt(5);
		if (time == 1) {
			return random10Minute(questionDate);
		} else if (time == 2) {
			return random11T60Minute(questionDate);
		} else if (time == 3) {
			return random61T0240Minute(questionDate);
		} else if (time == 4) {
			return random2T03Day(questionDate);
		} else {
			return random4T07Day(questionDate);
		}
	}

	// ��һ���������6:00-24:00���������ʱ������18Сʱ֮ǰ���6-23�㣬18��֮�����18-23��
	@SuppressWarnings("deprecation")
	public static Date randomOneDay(Date questionDate) {
		Random random = new Random();
		int questionHours = questionDate.getHours();
		int hours = 0;
		if (questionHours >= 18) {
			hours = random.nextInt(23 - questionHours);
		} else {
			hours = 5 + random.nextInt(18 - questionHours);
		}
		return new Date(questionDate.getTime() + hours * 60 * 60 * 1000);
	}

	// �ڶ����������6:00-24:00��
	@SuppressWarnings("deprecation")
	public static Date randomTwoDay(Date questionDate) {
		Random random = new Random();
		int day = 1;
		int questionHours = questionDate.getHours();
		int hours = 0;
		if (questionHours >= 18) {
			hours = random.nextInt(23 - questionHours);
		} else {
			hours = 5 + random.nextInt(18 - questionHours);
		}
		int date = day * 24 + hours;
		return new Date(questionDate.getTime() + date * 60 * 60 * 1000);
	}

	// �������������6:00-24:00��
	@SuppressWarnings("deprecation")
	public static Date randomThreeDay(Date questionDate) {
		Random random = new Random();
		int day = 2;
		int questionHours = questionDate.getHours();
		int hours = 0;
		if (questionHours >= 18) {
			hours = random.nextInt(23 - questionHours);
		} else {
			hours = 5 + random.nextInt(18 - questionHours);
		}
		int date = day * 24 + hours;
		return new Date(questionDate.getTime() + date * 60 * 60 * 1000);
	}

	// �������������
	public static Date sevenDayOf(Date questionDate) {
		long date = 6 * 24;
		return new Date(questionDate.getTime() +  date * 60 * 60 * 1000);
	}

	// ���ݾ�̬����������ȡ��Ӧ����ֵ(֧��б�ܺ��»���)
	public static String getParam(String paramStr, String paramName) {
		if (paramStr == null || paramStr.trim().equals("")) {
			return "";
		}
		if (paramName == null || paramName.trim().equals("")) {
			return "";
		}
		paramStr = paramStr.replaceAll("^(/||_)*", "/").replaceAll("(/||_)*$", "");
		Pattern p = Pattern.compile("(/||_)" + paramName + "[^(/||_)]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(paramStr);
		String result = "";
		if (m.find()) {
			result = m.group().replaceFirst("(/||_)" + paramName, "");
		}
		return result;
	}

	/**
	 * �ھݾ�̬�������������ӱ���,�ظ�����,��������ֵ
	 * <code> addParam("/k%E5%A4%A9%E5%A4%A9/p4/","k����") </code>
	 * 
	 * @param param ���б����� ����/��ͷ,��/��β ��� nvvvvv/ ���ӳ�
	 * @param addParam ���ӵı����� nvvvvv
	 */
	public static String addParam(String param, String addParam) {
		if (T.isBlank(addParam)) {
			return param;
		}
		if (T.isBlank(param)) {
			return addParam + "/";
		}
		String k = addParam.substring(0, 1);
		if (!param.startsWith("/")) {
			param = "/" + param;
		}
		param = param.replaceAll("/" + k + "[^/]*/", "/");
		if (!param.endsWith("/")) {
			param = param + "/";
		}
		param = param + addParam + "/";
		if (param.startsWith("/")) {
			param = param.replaceFirst("/", "");
		}
		return param;
	}

	public static String toLowercaseStrHead(String str) {
		if (str == null) {
			return null;
		}
		if (str.length() > 0) {
			return str.substring(0, 1).toLowerCase() + (str.length() > 1 ? str.substring(1) : "");
		} else {
			return "";
		}
	}

	public static String filtCode(String str) {
		if (str == null || str.equals("")) {
			return "";
		}
		str = T.replace(str, "<", "&lt;");
		str = T.replace(str, ">", "&gt;");
		// ���·�ʽie��֧��
		// str = T.replace(str, "'", "&apos;");
		// str = T.replace(str, "\"", "&quot;");
		str = T.replace(str, "'", "&#039;");
		str = T.replace(str, "\"", "&#034;");
		return str;
	}

	public static String filtCode(String str, boolean onlyScript) {
		if (str == null || str.equals("")) {
			return "";
		}
		if (onlyScript) {
			str = replaceAll(str, "<(script)([^/]?[^<>]*)/>", "&lt;$1$2/&gt;", Pattern.CASE_INSENSITIVE);
			str = replaceAll(str, "<(script)([^/]?[^<>]*)>([^<>]*)</?(script)>", "&lt;$1$2&gt;$3&lt;/$4&gt;", Pattern.CASE_INSENSITIVE);
			// <meta http-equiv="refresh"
			// content="0;url=http://www.zm345.cn/"
			// target="_blank" />
			str = T.replaceAll(str, "<(m|M)(e|E)(t|T)(a|A)([^/]?[^<>]*)/>", "&lt;m-e-t-a$5$2/&gt;", Pattern.CASE_INSENSITIVE);
		} else {
			str = filtCode(str);
		}
		return str;
	}

	public static String replaceAll(String text, String regex, String with, int flag) {
		return Pattern.compile(regex, flag).matcher(text).replaceAll(with);
	}

	/**
	 * �ж��Ƿ���������
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isContains(List list, Object obj) {
		return list.contains(obj);
	}

	/**
	 * ��ȡͷ���ַ
	 * 
	 * @param id ���
	 * @param uri ͼƬ��ַǰ׺
	 * @return
	 */
	public static String face(long id, String uri) {
		if (id <= 0) {
			return "";
		}
		String picPath = uri + "/";
		String aid = String.valueOf(id);
		for (int i = 0, s = aid.length(); i < s; i++) {
			int k = (i + 2) <= s ? (i + 2) : (i + 1);
			picPath += aid.substring(i, k) + "/";
			i++;
		}
		picPath = picPath + aid;
		return picPath;
	}

	/**
	 * ��ȡ��ͬ�ߴ�ͷ���ַ
	 * http://i[1-5].3conline.com/images/upload/upc/face/29/52/13/6/2952136_100x100.gif
	 * @param id ���
	 * @param uri ͼƬ��ַǰ׺
	 * @param suffix �ߴ� ����50x50
	 * @return
	 */
	public static String face(long userId, String uri, String suffix) {
		String id = String.valueOf(userId);
		long no = 1;
		if (uri != null) {
			uri = uri.replace("(?)", String.valueOf(no));
		}
		StringBuilder sb = new StringBuilder(uri + "/");
		for (int i = 0, c = id.length(); i < c; i += 2) {
			sb.append(id.charAt(i));
			if (i < c - 1) {
				sb.append(id.charAt(i + 1));
			}
			sb.append('/');
		}
		return sb.append(id).append("_").append(suffix).toString();
	}

	/**
	 * ��ȡ��ͬ�ߴ��ͼƬ��ַ
	 * 
	 * @param url ͼƬ��ַ
	 * @param size �ߴ� ����50x50
	 * @return
	 */
	public static String getPicUrlBySize(String url, String size) {
		if (url == null || url.trim().length() == 0) {
			return "";
		}
		if (!isBlank(size) && url.indexOf("_" + size) > 0) {
			return url;
		}

		int pos = url.lastIndexOf(".");
		if (pos == -1) {
			return url;
		}
		int hasFrame = url.lastIndexOf("_320x240");// д��'��������'�ĵ�ַ
		if (hasFrame > 0) {
			url = url.replaceAll("_320x240", "");
			pos = url.lastIndexOf(".");
		}
		String temp_1 = url.substring(0, pos);
		String temp_2 = url.substring(pos);
		return temp_1 + "_" + size + temp_2;
	}

	/**
	 * �ֽ�request.getParameterMap()
	 * 
	 * @param params
	 * @param name
	 * @return
	 */
	public static String getParameter(Map<String, String[]> params, String name) {
		String[] tmp = params.get(name);
		String result = null;
		if (tmp != null && tmp.length > 0) {
			result = tmp[0];
		}
		return result;
	}

	public static long getPendingTime(Date lastUpdate) {
		if (lastUpdate != null) {
			long now = T.getNow().getTime();
			long pendingTime = now - lastUpdate.getTime();
			return pendingTime;
		} else {
			return 0;
		}
	}

	/*
	 * ת���Ѷ�����, v=3.5 star='*'
	 */
	public static String stars(String v, String star, String halfStar) {
		String[] vs = v.split("\\.");
		int nums = -1;
		try {
			nums = Integer.parseInt(vs[0]);
		} catch (Exception e) {
		}
		String stars = "";
		for (int i = 0; i < nums; i++) {
			stars += star;
		}
		if (vs.length > 1) {// has half
			stars += halfStar;
		}
		return stars;
	}

	// ��ȡ��������ж���ʣ��ʱ��
	public static String getRemainTimes(Date endTime) {
		Date now = T.getNow();
		long itv = endTime.getTime() - now.getTime();
		if (itv <= 0) {
			return "1Сʱ";
		}
		long hours = itv / (3600 * 1000l);
		long days = hours / 24;
		return days + "��" + ((hours - days * 24) > 0 ? (hours - days * 24) + "Сʱ" : "");
	}

	// ��ȡ��������ж�����

	public static long getRemainDay(Date endTime) {
		Date now = T.getNow();
		long itv = endTime.getTime() - now.getTime();
		if (itv <= 0) {
			return 0;
		}
		long hours = itv / (3600 * 1000l);
		long days = hours / 24;
		return days;
	}

	// �������䣬��ȷ����
	public static String getAge(Date birthDay, boolean showMonth) {
		if (birthDay == null) {
			return "����";
		}
		Calendar cal = Calendar.getInstance();
		if (cal.before(birthDay)) {
			return "����";
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(birthDay);

		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		// showMonth = false;
		// int yearNow = 2009;
		// int monthNow = 1;
		// int dayOfMonthNow = 1;
		//
		// int yearBirth = 2008;
		// int monthBirth = 11;
		// int dayOfMonthBirth = 1;

		int age = yearNow - yearBirth;
		int month = monthNow - monthBirth;
		if (monthNow < monthBirth) {
			age--;
			if (dayOfMonthNow < dayOfMonthBirth) {
				month--;
			}
			if (month < 0) {
				month += 12;
			}
		} else if (monthNow == monthBirth) {
			if (dayOfMonthNow < dayOfMonthBirth) {
				month--;
			}
			if (month < 0) {
				age--;
				month += 12;
			}
		} else {
			;
		}

		String str = "";
		if (age > 0) {
			str += age + "��";
		}
		if (month > 0 && month < 12) {
			str += month + "����";
		}
		if (age <= 0 && month <= 0) {
			str += "δ����";
		}
		// ֻ��ʾ������
		if (!showMonth) {
			str = str.replaceFirst("\\d+����", "").replaceFirst("δ����", "");
		}
		if (str.equals("")) {
			str = "����";
		}
		return str;
	}

	// �������䣬��ȷ���գ�һ���°�30�����
	public static String getAge3(Date birthDay) {

		Calendar calNow = Calendar.getInstance();
		Calendar calBirth = Calendar.getInstance();
		calBirth.setTime(birthDay);
		// calBirth.set(Calendar.YEAR, 2007);
		// calBirth.set(Calendar.MONTH, 4);
		// calBirth.set(Calendar.DAY_OF_MONTH, 31);

		int yearNow = calNow.get(Calendar.YEAR);
		int monthNow = calNow.get(Calendar.MONTH) + 1;
		int dayNow = calNow.get(Calendar.DAY_OF_MONTH);

		int yearBirth = calBirth.get(Calendar.YEAR);
		int monthBirth = calBirth.get(Calendar.MONTH) + 1;
		int dayBirth = calBirth.get(Calendar.DAY_OF_MONTH);

		int year = yearNow - yearBirth;
		int month = monthNow - monthBirth;
		int day = dayNow - dayBirth;
		System.out.println(year + "-" + month + "-" + day);
		if (day < 0) {
			month--;
			day += 30;
		}
		if (month < 0) {
			year--;
			month += 12;
		}
		String str = "";
		if (year > 0) {
			str += year + "��";
		}
		if (month > 0) {
			str += month + "����";
		}
		if (day > 0) {
			str += day + "��";
		}
		return str;
	}

	/**
	 * ����������Ӧ���ǡ��ᡱ����Ӧ���ǡ����������磺4�����µ�ǰ̨��ʾӦ��4���£�2��7���µ�Ӧ����ʾ2�ꡣ
	 * 
	 * @param now
	 * @param birthDay
	 * @param showMonth
	 * @return
	 */
	public static String getAge2(Date birthDay, boolean showMonth) {
		if (birthDay == null) {
			return "����";
		}
		Calendar cal = Calendar.getInstance();
		Date now = T.getNow();
		cal.setTime(now);
		if (now.before(birthDay)) {
			return "����";
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(birthDay);

		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;
		int month = monthNow - monthBirth;

		if (dayOfMonthNow < dayOfMonthBirth) {
			if (monthNow <= monthBirth) {
				age--;
			}
			month--;
		} else {
			if (monthNow < monthBirth) {
				age--;
			}
		}
		if (month < 0) {
			month += 12;
		}

		String str = "";
		if (age > 0) {
			str += age + "��";
		}
		if (month > 0 && month < 12) {
			str += month + "����";
		}
		if (age <= 0 && month <= 0) {
			str += "δ����";
		}
		// ֻ��ʾ������
		if (!showMonth) {
			str = str.replaceFirst("\\d+����", "").replaceFirst("δ����", "");
		}
		if (str.equals("")) {
			str = "����";
		}
		return str;
	}

	public static String getAge4(Date birthDay) {
		if (birthDay == null) {
			return "����";
		}
		Calendar cal = Calendar.getInstance();
		Date now = T.getNow();
		cal.setTime(now);
		if (now.before(birthDay)) {
			return "����";
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(birthDay);

		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;
		int month = monthNow - monthBirth;

		if (dayOfMonthNow < dayOfMonthBirth) {
			if (monthNow <= monthBirth) {
				age--;
			}
			month--;
		} else {
			if (monthNow < monthBirth) {
				age--;
			}
		}
		if (month < 0) {
			month += 12;
		}

		String str = "";
		if (age > 0) {
			str += age + "��";
		} else if (month > 0 && month < 12) {
			str += month + "����";
		} else if (age <= 0 && month <= 0) {
			str += "δ����";
		}

		if (str.equals("")) {
			str = "����";
		}
		return str;
	}

	// ��ҳbaby��ʾ������� ��ȷ����
	public static String getAge5(Date birthDay) {
		String weekTip = "";
		int days = T.getDays(birthDay, T.getNow());
		int year = days / 365;// >=0 0-6 6����
		int month = days % 365 / 30;// 0-12
		int week = days % 365 % 30 / 7;// 0-4
		int day = days % 365 % 30 % 7;// 1-6 1��
		if (birthDay.getTime() < T.getNow().getTime()) {
			if (year >= 6) {
				weekTip = "����" + year + "��";
			} else if (year >= 1 && year < 6) {
				weekTip = "����" + year + "��" + month + "��";
				if (month == 12) {
					weekTip = "����" + (year + 1) + "��";
				} else if (month == 0) {
					weekTip = "����" + year + "��";
				}
			} else if (year >= 0 && year < 1) {
				if (month > 0 && month <= 12 && week < 4 && week > 0) {
					weekTip = "����" + month + "�µ�" + week + "��";
				} else if (month == 1 && week == 0 && day == 0) {
					weekTip = "��������";
				} else if (month > 0 && month <= 12 && week == 0) {
					weekTip = "����" + month + "�µ�1��";
				}
				if (month > 0 && month < 12 && week == 4) {
					weekTip = "����" + (month + 1) + "��";
				} else if (month == 12 && week == 4) {
					weekTip = "����1��";
				} else if (year == 0 && month == 0 && week == 0 && day <= 6) {
					weekTip = "����������" + day + "��";
				} else if (year == 0 && month == 0 && week == 4 && day < 6) {
					weekTip = "����������" + week + "��";
				} else if (year == 0 && month == 0 && week < 4) {
					weekTip = "����������" + week + "��";
				}
			}
		}
		return weekTip;
	}

	public static void main(String[] args) {
	}

	/**
	 * ����SNS�û�����ֵ����������ʾ���ݡ�
	 * 
	 * @param totalRank ϵͳ�еȼ�������
	 * @param myRank �û��ĵȼ�
	 * @param fullPixels ����������ʱ������ֵ
	 * @return
	 */
	public static int getSNSRank(int totalRank, int myRank, int fullPixels) {
		int iRet = 0;
		if (totalRank <= 0) {
			return iRet;
		}
		double x = ((double) myRank / (double) totalRank) * fullPixels;
		return Double.valueOf(x).intValue();
	}

	/**
	 * �����ַ�����һ��ķָ���
	 * 
	 * @param str
	 * @return
	 */
	public static String filterSeparatorForTag(String str) {
		String tag = "";
		if (str != null && !str.equals("")) {
			tag = str.replaceAll("��", ",").replaceAll(":", ",").replaceAll("��", ",").replaceAll(";", ",").replaceAll("��", ",").replace("��", ",")
					.replaceAll(" ", ",");
		}
		return tag;
	}

	/**
	 * *
	 * �������,�ؼ���������ʽ.
	 * 
	 * @param s
	 * @param key
	 * @param style
	 * @return
	 *         eg. T.addStyle(s,key,
	 *         "&lt;i class=\"error\"&gl;$1&lt;/i&gl;")
	 */
	public static String addStyle(String s, String key, String style) {
		String tem = "";
		if (T.isBlank(s)) {
			tem = s;
		} else {
			if (T.isBlank(key)) {
				tem = s;
			} else {
				if (s.indexOf(key) == -1) {
					tem = s;
				} else {
					tem = s.replaceAll(key, style);
				}
			}
		}
		return tem;
	}

	public static BigDecimal toBigDecimal(String o) {
		return T.isBlank(o) ? null : new BigDecimal(o);
	}

	public static Integer toInteger(String o) {
		return T.isBlank(o) ? null : new Integer(o);
	}

	public int doubleToInt(double d) {
		return (int) d;
	}

	public static String addLegStr(String diary, String legstr) {
		return diary.replaceAll("</div>", "</div>" + legstr).replaceAll("<br\\s*/?>", "<br>" + legstr);
	}

	public static Double toDouble(String o) {
		return T.isBlank(o) ? null : new Double(o);
	}

	/**
	 * *
	 * ���ص�ǰ�����������
	 * 
	 * @return
	 */
	public static ClassLoader getClassLoader() {
		return T.class.getClassLoader();
	}

	public static String tHTML(String str) {
		if (T.isBlank(str)) {
			return "";
		}
		str = T.replace(str, "&", "&amp;");
		str = T.replace(str, "<", "&lt;");
		str = T.replace(str, ">", "&gt;");
		str = T.replace(str, "'", "&#039;");
		str = T.replace(str, "\"", "&#034;");
		return str;
	}

	public static String rHTML(String str) {
		if (T.isBlank(str)) {
			return "";
		}
		str = T.replace(str, "&amp;", "&");
		str = T.replace(str, "&lt;", "<");
		str = T.replace(str, "&gt;", ">");
		str = T.replace(str, "&#039;", "'");
		str = T.replace(str, "&#034;", "\"");
		return str;
	}

	public static String getIp(HttpServletRequest request) {
		String remoteAddr = request.getRemoteAddr();
		String forwarded = request.getHeader("X-Forwarded-For");
		String realIp = request.getHeader("X-Real-IP");
		String ip = null;
		if (realIp == null) {
			if (forwarded == null) {
				ip = remoteAddr;
			} else {
				ip = remoteAddr + "/" + forwarded;
			}
		} else {
			if (realIp.equals(forwarded)) {
				ip = realIp;
			} else {
				ip = realIp + "/" + forwarded.replaceAll(", " + realIp, "");
			}
		}
		return ip;
	}

	public static boolean validate(HttpServletRequest request) {
		if (!request.getMethod().equalsIgnoreCase("POST")) {
			return false;
		}
		return true;
	}

	public static Integer[] getRandomInts(int max, int count, boolean canRepeat) {
		if (max > 0 && count > 0) {
			Random r = new Random();
			if (canRepeat) {
				List<Integer> is = new ArrayList<Integer>();
				while (is.size() < count) {
					is.add(r.nextInt(max));
				}
				return is.toArray(new Integer[count]);
			} else {
				Set<Integer> is = new HashSet<Integer>(count);
				count = count > max ? max : count;
				while (is.size() < count) {
					is.add(r.nextInt(max));
				}
				return is.toArray(new Integer[count]);
			}
		}

		return new Integer[0];
	}

	public static String roundFileSize(long fs) {
		if (fs < 1024) { // С��1M
			return fs + "K";
		} else if (fs < 1048576) { // С��1G
			BigDecimal s = new BigDecimal(fs).divide(new BigDecimal(1024), 1, BigDecimal.ROUND_HALF_UP);
			return s.floatValue() + "M";
		} else {
			BigDecimal s = new BigDecimal(fs).divide(new BigDecimal(1048576), 1, BigDecimal.ROUND_HALF_UP);
			return s.floatValue() + "G";
		}
	}

	/**
	 * �Ը�ת�����ε�����
	 * 
	 * @param str
	 * @return
	 */
	public static String antiFiltCode(String str) {
		if (str == null || str.equals("")) {
			return "";
		}
		str = T.replace(str, "&lt;", "<");
		str = T.replace(str, "&gt;", ">");
		str = T.replace(str, "&#039;", "'");
		str = T.replace(str, "&apos;", "'");
		str = T.replace(str, "&quot;", "\"");
		str = T.replace(str, "&amp;", "&");
		return str;
	}

	/**
	 * ȥ��ubb����
	 * 
	 * @param str
	 * @param isKeepImg �Ƿ�ubbͼƬ��ǩת����html��ʽ
	 * @return
	 */
	public static String removeUBB(String str, boolean isKeepImg) {
		if (str == null || str.equals("")) {
			return "";
		}
		if (isKeepImg) {
			str = str.replaceAll("(?i)\\[img\\]http://", "<img class='image_link' src=\"http://").replaceAll("(?i)\\[/img\\]", "\"/>");
		} else {
			str = str.replaceAll("(?i)\\[img\\](.*?)\\[/img\\]", "");
		}
		str = str.replaceAll("\\[(\\w+)(\\=)(.*?)\\]", "");
		str = str.replaceAll("\\[(\\/\\w+)\\]", "");
		str = str.replaceAll("\\[[a-z0-9A-Z_]+\\]", "");
		return str;
	}

	/**
	 * ��ȡ�����������httpclientҪ���ô���
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Properties getProxy() throws Exception {
		String path = "/contactsImporterConnect.properties";
		Properties properties = null;
		InputStream inputStream = null;

		try {
			properties = new Properties();
			inputStream = T.class.getResourceAsStream(path);
			properties.load(inputStream);
		} catch (Exception e) {
			throw e;
		} finally {
			if(null != inputStream) {
				inputStream.close();
			}
		}
		return properties;
	}

	// ���� �м����£������£����꣬���꼸���� ����� ʱ��

	public static Map<String, Date> getAgeDateBySearchWord(String searchWord) {
		Map<String, Date> birthdayMap = null;

		Date begin = null;
		Date end = null;
		if (searchWord == null || T.isBlank(searchWord)) {
			return null;
		}

		Date theDay = T.getNow();
		int number = 0;
		if (searchWord.indexOf("��") >= 0) {
			number = T.intValue(searchWord.substring(1, 2), 0);
			begin = T.addMonth(theDay, 10 - number);
		} else if (searchWord.indexOf("����") >= 0 || searchWord.indexOf("��") >= 0) {
			if (searchWord.indexOf("��") >= 0) {
				if (searchWord.indexOf("����") >= 0) {
					number = T.intValue(searchWord.substring(0, 1), 0);
					int month = T.intValue(searchWord.substring(2, 3), 0);
					begin = T.addYear(theDay, -number);
					begin = T.addMonth(begin, -month);
				} else {
					number = T.intValue(searchWord.substring(0, 1), 0);
					begin = T.addYear(theDay, -number);
				}
			} else {
				number = T.intValue(searchWord.substring(0, 1), 0);
				begin = T.addMonth(theDay, -number);
			}
		}

		if (begin != null) {
			begin = T.getMonthStart(begin);
			end = begin;
			if (T.getYear(begin) <= 2006) {
				System.out.println("--" + T.formatDateTime(begin));
				begin = T.addMonth(begin, -T.getMonth(begin));
				end = T.addMonth(T.addYear(end, 1), -T.getMonth(end) - 1);
			}
			birthdayMap = new HashMap<String, Date>();
			birthdayMap.put("begin", begin);
			birthdayMap.put("end", end);
		}
		return birthdayMap;
		// return begin;
	}

	/**
	 * ���˵����е�html��ǩ����ֹjsע��.
	 * 
	 * @param str ��Ҫ������ַ���
	 * @param except �Ƿ���<a></a>,<img>,<br>
	 *        ��html��ǩ��trueΪ�����������е�<a></a>,<img>,<br>
	 *        ��ǩ��
	 * @return ������ȡ���ַ���
	 */
	public static String filterHTML(String str, boolean except) {
		if (str == null || str.equals("")) {
			return "";
		}
		if (except) {
			str = str.replaceAll("<(a|A) ([^/]?[^<>]*)>(.+?)</?(a|A)>", "#alink($2)($3)#");
			str = str.replaceAll("<(i|I)(m|M)(g|G) ([^/]?[^<>]*)>", "!imglink($4)!");
			str = str.replaceAll("<(b|B)(r|R)([^/]?[^<>]*)>", "!brlink($3)!");
		}
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("  ", "&nbsp;&nbsp;");
		str = str.replaceAll("\r\n", "\n");
		str = str.replaceAll("\n", "<br>");
		str = str.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		if (except) {
			str = str.replaceAll("#alink\\((.*?)\\)\\((.*?)\\)#", "<a $1>$2</a>");
			str = str.replaceAll("!imglink\\((.*?)\\)!", "<img $1>");
			str = str.replaceAll("!brlink\\((.*?)\\)!", "<br$1>");
		}
		return str;
	}

	/**
	 * �ض��ַ�������������ضϺ󲹳��׺suffix
	 * 
	 * @param value ��Ҫ�ضϵ�ԭ�ַ���
	 * @param length ��Ҫ�ضϵĳ���
	 * @param suffix ����ĺ�׺
	 * @return �ضϺ�����ַ���
	 * @author ���� 2011-3-10
	 */
	public static String cutString2(String value, int length, String suffix) {
		String strValue = value;
		boolean cut = false;
		int lengthByte = length * 2;
		if (value == null || value.equals("")) {
			return "";
		}
		try {
			if (strValue.length() < length) {
				return strValue;
			}
			if (strValue.length() > lengthByte) {
				strValue = strValue.substring(0, lengthByte);
				cut = true;
			}

			byte[] bytes = strValue.getBytes("GBK");
			if (bytes.length <= lengthByte) {
			} else {
				cut = true;
				byte[] newBytes = new byte[lengthByte];
				System.arraycopy(bytes, 0, newBytes, 0, lengthByte);
				strValue = new String(newBytes, "GBK");
				if (strValue.charAt(strValue.length() - 1) == (char) 65533) {
					strValue = strValue.substring(0, strValue.length() - 1);
				}
			}
		} catch (Exception ex) {
			System.out.println("��ȡ�ַ�������" + ex);
			return "";
		}

		if (!cut) {
			suffix = "";
		}
		return strValue + suffix;
	}

	/**
	 * �����ֽ�����ȡ�ַ���
	 * 
	 * @param value ��Ҫ������ַ���
	 * @param byteLen ��Ҫ��ȡ�ĳ��ȣ��ֽ�����
	 * @return ������ȡ���ַ���
	 */
	public static String byteSubstring2(String value, int byteLen) {
		if (value == null) {
			return "";
		}
		if (value.length() < byteLen / 2) {
			return value;
		}

		String strValue = null;
		try {
			if (value.length() > byteLen) {
				strValue = value.substring(0, byteLen);
			} else {
				strValue = value;
			}

			byte[] bytes = strValue.getBytes("GBK");
			if (bytes.length > byteLen) {
				strValue = new String(bytes, 0, byteLen, "GBK");

				if (strValue.charAt(strValue.length() - 1) == (char) 0xFFFD) {
					strValue = strValue.substring(0, strValue.length() - 1);
				}
			}
		} catch (Exception ex) {
			return value;
		}

		return strValue;
	}

	public static String toHTML2(String str, String enter, boolean exceptA) {
		if (str == null || str.equals("")) {
			return "";
		}
		// ������A
		if (exceptA) {
			str = str.replaceAll("<(a|A) ([^/]?[^<>]*)>(.+?)</?(a|A)>", "#alink($2)($3)#");
		}// System.out.println(str);
		/*
		 * str = replace(str, "&", "&amp;"); str =
		 * replace(str, "<", "&lt;");
		 * str = replace(str, ">", "&gt;"); str =
		 * replace(str, " ",
		 * "&nbsp;&nbsp;"); str = replace(str, "\r\n",
		 * "\n"); str = replace(str,
		 * "\n", enter); str = replace(str, "\t",
		 * "&nbsp;&nbsp;&nbsp;&nbsp;");
		 */
		// str = replace(str, "'", "��");
		// str = replace(str, "\"", "��");
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("  ", "&nbsp;&nbsp;");
		str = str.replaceAll("\r\n", "\n");
		str = str.replaceAll("\n", enter);
		str = str.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		if (exceptA) {
			str = str.replaceAll("#alink\\((.*?)\\)\\((.*?)\\)#", "<a $1>$2</a>");
		}
		return str;
	}

	public static String md5(String text) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(text.getBytes("utf-8"));
			StringBuilder buf = new StringBuilder();
			for (int i = 0; i < digest.length; i++) {
				String b = Integer.toHexString(0xFF & digest[i]);
				if (b.length() == 1) {
					buf.append('0');
				}
				buf.append(b);
			}
			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return text;
	}

	/**
	 * ֻ������,�� split ����
	 * 
	 * @param value Ҫ����ֵ
	 * @param split �����
	 * @return boolean true: ���� �� false ������
	 */
	public static boolean bolNumbers(String value, String split) {
		if (isBlank(value)) {
			return false;
		}
		String regex = "^\\d+(" + split + "\\d+)*$";
		return value.matches(regex);
	}

	/**
	 * ֻ��Ӣ���ַ�/����/�»���,�� split ����
	 * 
	 * @param value Ҫ����ֵ
	 * @param split �����
	 * @return boolean true: ���� �� false ������
	 */
	public static boolean bolStrings(String value, String split) {
		if (isBlank(value)) {
			return false;
		}
		String regex = "^\\w+(" + split + "\\w+)*$";
		return value.matches(regex);
	}

	public static void setCookie(HttpServletResponse response, String name, String value, String path, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setSecure(false);
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	/**
	 * ɾ��cookie
	 */
	public static void clearCookie(HttpServletRequest request, HttpServletResponse response, String path) {
		Cookie[] cookies = request.getCookies();
		try {
			for (int i = 0; i < cookies.length; i++) {
				// System.out.println(cookies[i].getName() +
				// " : " + cookies[i].getValue());
				Cookie cookie = new Cookie(cookies[i].getName(), null);
				cookie.setMaxAge(0);
				cookie.setPath(path);// �����㴴��cookie��·��������д
				response.addCookie(cookie);
			}
		} catch (Exception ex) {
			System.out.println("ɾ��Cookies�����쳣��");
		}
	}

	/**
	 * ��cookie����ֵ
	 */
	public static String getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();

		if (cookies == null) {
			return "";
		}

		try {
			for (int i = 0; i < cookies.length; i++) {
				// System.out.println(cookies[i].getName() +
				// ":" + cookies[i].getValue());
				if (cookies[i].getName().equalsIgnoreCase(name)) {
					String cookieValue = cookies[i].getValue().toString();
					return cookieValue;
				}
			}
			return "";
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
			System.out.println("��ȡCookies�����쳣��");
			return "";
		}
	}

	public static final String refresh(HttpServletRequest request) {
		return defaultIfEmpty(request.getParameter("refresh"), "false");
	}

	public static String defaultIfEmpty(String str, String defaultStr) {
		return isEmpty(str) ? defaultStr : str.trim();
	}

	public static String splitTag(String input) {
		return splitTag(input, 0, null);
	}

	public static String splitTag(String input, int length) {
		return splitTag(input, length, "����");
	}

	public static String splitTag(String input, int length, String fill) {
		if (input == null || input.trim().equals("")) {
			return input;
		}
		// ȥ������htmlԪ��,
		String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "");
		str = str.replaceAll("<[a-zA-Z]+[1-9]?[^><]*>", "");
		str = str.replaceAll("</[a-zA-Z]+[1-9]?>", "");
		if (length > 0) {
			int len = str.length();
			if (len <= length) {
				return str;
			} else {
				str = str.substring(0, length);
				if (fill != null) {
					str += fill;
				}
			}
		}
		return str;
	}

	public static int randomInt(int from, int to) {
		Random r = new Random();
		return from + r.nextInt(to - from);
	}

	

	
	/**
	 * ����ָ���ĳ��Ƚ��֣�֧������
	 * @param value
	 * @param byteLen
	 * @return
	 * Created by Jack, 2013-7-19.
	 */
	public static String subbytesGBK(String value, Integer byteLen) {
		if (value == null) {
			return "";
		}
		if (value.length() < byteLen / 2) {
			return value;
		}
		String strValue = null;
		try {
			if (value.length() > byteLen) {
				strValue = value.substring(0, byteLen);
			} else {
				strValue = value;
			}
			byte[] bytes = strValue.getBytes("GBK");
			if (bytes.length > byteLen) {
				strValue = new String(bytes, 0, byteLen, "GBK");
				// 0xFFFD
				// ��ο�java.nio.charset.CharsetDecoder.java
				if (strValue.charAt(strValue.length() - 1) == (char) 0xFFFD) {
					strValue = strValue.substring(0, strValue.length() - 1);
				}
			}
		} catch (Exception ex) {
			return value;
		}
		return strValue;
	}
	
        
	/**
	 *  ������������ڵĵڼ���
	 * @return
	 */
	public static int getDayOfWeek() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(System.currentTimeMillis()));
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1) {
			dayOfWeek = 7;
		} else {
			dayOfWeek = dayOfWeek - 1;
		}
		return dayOfWeek;
	}
	
	/**
	 * ȥ�����ķֺ�
	 * @param target
	 * @return
	 * Created by Jack, 2013-11-20.
	 */
	public static String cnFilter(String target) {
		if (isBlank(target)) {
			return null;
		}
		return target.replaceAll("��", ";");
	}
	/**
	 * ���ݳ���ȡ�ַ�
	 * @param value
	 * @param length
	 * @return
	 */
	public static String subString(String value, Integer length) {
		if (T.isBlank(value)) {
			return "";
		}
		if (value.length() > length) {
			return value.substring(0, 30);
		} else {
			return value;
		}
	}
	
	/**
	 * �˷����������������ݣ�
	 * ��ԭ&lt;����ԭ&gt;��ȥ�����͡�������HTML��ǩ��Ļ��в��ģ������Ķ���Ϊ<p>��ǩ
	 * @param str
	 * @return
	 * Created by Jack, 2013-11-27.
	 */
	public static String toOutput(String str) {
		if (isBlank(str)) {
			return "";
		}
		str = str.trim();
		str = T.replace(str, "&lt;", "<");
		str = T.replace(str, "&gt;", ">");
		str = T.replace(str, "'", "");
		str = T.replace(str, "\"", "");
		str = str.replaceAll("[^<[A-Za-z0-9]>]\n", "$0</p><p>");
		return str;
	}
	
	/**
	 * �˷���ֻ��ԭ���з�\n����Ϊ<br>
	 * @param str
	 * @return
	 * Created by Jack, 2013-11-27.
	 */
	public static String toOutput_2(String str) {
		if (isBlank(str)) {
			return "";
		}
		str = str.trim();
		str = T.replace(str, "\n", "<br>");
		return str;
	}
        
	/**
	 * ���������Ÿ�ΪӢ�ĵ�����
	 * 
	 * @param str
	 * @return
	 * @author luhaichao<luhaichao@pconline.com.cn>,
	 *         2013-12-10.
	 */
	public static String toOutput_3(String str) {
		if (isBlank(str)) {
			return "";
		}
		str = str.replaceAll("��|��", "'");
		return str;
	}

	/**
	 * ��ת�Ʒ���&lt;���޸�Ϊ��<������&gt;���޸�Ϊ��>��.
	 * 
	 * @param str
	 * @return
	 * @author luhaichao<luhaichao@pconline.com.cn>,
	 *         2013-12-10.
	 */
	public static String toOutput_4(String str) {
		if (isBlank(str)) {
			return "";
		}
		str = str.replaceAll("&lt;", "<");
		str = str.replaceAll("&gt;", ">");
		return str;
	}
	
	/**
	 * ��ȡ���ֵĺ�nλ��
	 * @param objectId
	 * @param length
	 * @return
	 * Created by Jack, 2013-12-31.
	 */
	public static String cutLong(Long objectId, Integer length) {
		if (objectId <= 0) {
			return "";
		}
		String objectIdStr = String.valueOf(objectId);
		if (objectIdStr.length() > length) {
			return objectIdStr.substring(0, objectIdStr.length() - length);
		} else {
			return objectIdStr;
		}
	}
	
	/**
	 * ���˵�a��ǩ��
	 * @param str
	 * @return
	 * Created by Jack, 2013-12-31.
	 */
	public static String cutA(String str) {
		if (isBlank(str)) {
			return "";
		}
		str = str.replaceAll("<a.*?\">", "");
		str = str.replaceAll("</a>", "");
		return str;
	}
	
	/**
	 * ȥ��A��ǩ����������
	 * @param content
	 * @return
	 * Created by Jack, 2014-2-21.
	 */
	public static String rmATag(String content) {
		if (isBlank(content)) {
			return "";
		}
		return content.replaceAll("<(a|A) ([^/]?[^<>]*)>(.+?)</?(a|A)>", "$3");
	}
	
	/**
	 * �����ؼ��ʱ��
	 * @param content
	 * @param keyword
	 * @return
	 * Created by liningbo, 2014-3-5.
	 */
	public static String toRed(String content ,String keyword) {
		if (content == null) {
			return "";
		}
		content = content.replaceAll(keyword, "<em class='red'>"+keyword+"</em>");
		return content;
	}
	
	/**
	 * ����HTML��ǩ
	 * @param content
	 * @return
	 * Created by Jack, 2014-4-10.
	 */
	public static String cleanHtml(String content) {
		if (isBlank(content)) {
			return "";
		}
		return content.replaceAll("(<.*?>)|(��*)|(\\s*)", "");
	}
	
	/**
	 * ��ȡ�ļ�����׺
	 * @param fileName
	 * @return
	 * Created by Jack, 2014-4-17.
	 */
	public static String getExtension(String fileName) {
		if (isBlank(fileName) || !fileName.contains(".")) {
			return "";
		}
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}
	
	
	/**
	 * ͨ��ָ���ı���ת���ַ������� 
	 * new String(bytes, charset)
	 * @param content
	 * @param charset
	 * @return
	 * Created by Jack, 2014-4-30.
	 */
	public static String getDecContent(String content, String charset) {
		if (isBlank(content)) {
			return "";
		}
		try {
			content = new String(content.getBytes(), charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	
	/**
	 * ��ȡ�ٶ����ܿ�ids
	 * @param n (1=ר�����ѣ�2=ר�ң�3=���ף�4=̥��������5=��ƷС��ʾ)
	 * @param str
	 * @return
	 * @author liningbo, 2014-7-9.<br>
	 */
	public static List<Long> getIdList(int n, String str) {
		if (isBlank(str) || n < 1) {
			return null;
		}
		List<Long> list = new ArrayList<Long>();
		String[] s = str.split("\\|");
		if (s.length >= n && s[n - 1] != null) {
			String[] ids = s[n - 1].split(":");
			for (int i = 0; i < ids.length; i++) {
				list.add(Long.parseLong(ids[i]));
			}
			return list;
		} else {
			return null;
		}
	}
	
	/**
	 * ��ȡ����ƽ̨Ŀ¼title
	 * 
	 * @param n
	 * @param str
	 * @return
	 * @author liningbo, 2014-7-22.<br>
	 */
	public static String getTryoutTitle(Integer n, String str) {
		if (isBlank(str) || n < 1) {
			return null;
		}
		String[] s = str.split("\\|");
		if (s[n - 1].split(";").length > 0) {
			return s[n - 1].split(";")[0];
		} else {
			return null;
		}
	}

	/**
	 * ��ȡ����ƽ̨Ŀ¼url
	 * 
	 * @param n
	 * @param str
	 * @return
	 * @author liningbo, 2014-7-22.<br>
	 */
	public static String getTryoutUrl(Integer n, String str) {
		if (isBlank(str) || n < 1) {
			return null;
		}
		String[] s = str.split("\\|");
		if (s[n - 1].split(";").length > 1) {
			return s[n - 1].split(";")[1];
		} else {
			return null;
		}
	}
	
	/**
	 * �ٶ����ܿ�����
	 * @param str
	 * @return<br>
	 * @author liningbo, 2014-7-10.<br>
	 */
	public static String clean4baidu(String str) {
		if (isBlank(str)) {
			return null;
		}
		str = str.replaceAll("<p>(\\s|��|&nbsp;)*", "");
		str = replace(str, "</p>", "[br]");
		
		str = str.replaceAll("<strong>(\\s|��|&nbsp;)*", "[bold=");
		str = str.replaceAll("<strong style.*?\">", "[bold=");
		str = replace(str, "</strong>", "]");
		
		str = str.replaceAll("<img.*src=\"", "[picurl=");
		str = str.replaceAll(".jpg\" style.*/>", ".jpg]");
		
		return str;
	}
	
	
	
	
	/**
	 * ��ȡ�ַ�������0��remainLen�����ڸó��Ȳ�ʡ�Ժţ�null���ؿ��ַ���trimͷβ�հ��ַ�
	 * @param str          Ҫ��ȡ���ַ���
	 * @param remainLen    ��ȡ�ĳ���
	 * @return
	 * @author Dengxiaolin 2014-09-26
	 */
	public static String substr(String str, Integer remainLen) {
		if (isBlank(str)) {
			return "";
		} else {
			str = str.trim();
			int len = str.length();
			if (len <= remainLen) {
				return str;
			} else {
				return str.substring(0, remainLen) + "...";
			}
		}
	}
	
	/**
	 * ͨ��������ʽ�ж�ͼƬ�����Ƿ�ﵽ5��
	 * @param content
	 * @return<br>
	 * @author huangjinyuan, 2014��9��29��.<br>
	 */
	public static boolean judgeFivePic(String content) {
		boolean flag = true;
		//img=540,360
		String patt = "(\\[img(\\=\\d+\\,\\d+)?\\])([^\\[]+)(\\[/img\\])";
		Pattern pattern = Pattern.compile(patt);
		int count = 0;
		Matcher mat = pattern.matcher(content);
	    while(mat.find()) {
	  	  count++;
	    }
	    if(count < 5) {
	    	flag = false;
	    }
	    return flag;
	}
	
	/**
	 * ͨ��������ʽ��ȡ��һ��ͼƬ��URl
	 * @param content
	 * @return<br>
	 * @author huangjinyuan, 2014��9��30��.<br>
	 */
	public static String getFirstPicUrl(String content) {
		String patt = "(\\[img(\\=\\d+\\,\\d+)?\\])([^\\[]+)(\\[/img\\])";
		Pattern pattern = Pattern.compile(patt);
		Matcher mat = pattern.matcher(content);
		String picUrl = "";
		if(mat.find()) {
			picUrl = mat.group(3);
             return picUrl;
		}
		return picUrl;
	}
	

	
	/**
	 * ȡ����һ���ڵ����ʱ��
	 * @return<br>
	 * @author huangjinyuan, 2014��10��21��.<br>
	 */
	public static Date getRecent7RandomDate() {
		Date now = T.getNow();
		Date before = T.getSomeDate(now, -7);
		long date =  before.getTime() + (long)(Math.random()*(now.getTime() - before.getTime()));
		Date after = new Date(date);
		return after;
	}
	
	/**
	 * ��ȡժҪ�����˵�����[]�ַ�
	 * @param summary
	 * @return<br>
	 * @author huangjinyuan, 2014��10��22��.<br>
	 */
	public static String getReportSummary(String summary) {
		return summary.replaceAll("\\[(.*?)\\]", "");
	}
	
	/**
	 * ��ʽ��ʱ�� x��xСʱ��С��һ��Сʱ-������ֹ
	 * @param millseconds ������
	 * @return
	 */
	public static String formatTime(long millseconds) {
		if (millseconds < HOUR) {
			return "������ֹ";
		} else {
			StringBuffer sb = new StringBuffer();
			long day = millseconds / DAY;
			if (day > 0) {
				sb.append(day).append('��');
				millseconds = millseconds % DAY;
			}
			long hour = millseconds / HOUR;
			if (hour > 0) {
				sb.append(hour).append("Сʱ");
				millseconds = millseconds % HOUR;
			}
			
			/*long minute = millseconds / MINUTE;
			if (minute > 0) {
				sb.append(minute).append("��");
				millseconds = millseconds % MINUTE;
			}
			long second = millseconds / SECOND;
			if (second > 0) {
				sb.append(second).append('��');
				millseconds = millseconds % SECOND;
			}*/
		
			return sb.toString();
		}
	}
	
	/**
	 * ȥ��ʱ���ϵ�00:00:00��ʽ������������򷵻�ȫ��
	 * @param date
	 * @return<br>
	 * @author Jack, 2014-11-6.<br>
	 */
	public static String dateNotZero(Date date) {
		String dateString = format(date, "yyyy-MM-dd HH:mm:ss");
		if (isBlank(dateString)) {
			return "";
		}
		if (dateString.contains(" 00:00:00")) {
			return dateString.replaceAll(" 00:00:00", "");
		} else {
			return dateString;
		}
	}
	
	/**
	 * ��ʽ�����֣�������λС��
	 * @param number
	 * @return<br>
	 * @author Jack, 2014-10-12.<br>
	 */
	public static String formatNumber(double number) {
		DecimalFormat df = new DecimalFormat("0.##");
		return df.format(number);
	}
	
	/**
	 * ��ʽ�����֣��Զ����ʽ
	 * @param format - ��ʽ������#.#
	 * @param number
	 * @return<br>
	 * @author Jack, 2014-10-12.<br>
	 */
	public static String formatNumber(double number, String format) {
		DecimalFormat df = new DecimalFormat(format);
		return df.format(number);
	}
	
	
	/**
	 * ��ȡӦ��·��
	 * @param request
	 * @return
	 * @author huangjinyuan
	 * @created 2017-3-29
	 * @copyright Excellence co.
	 */
	public static String getApplicationRealPath(HttpServletRequest request) {
		String requestUrl = request.getScheme() //��ǰ����ʹ�õ�Э��
			    +"://" + request.getServerName()//��������ַ 
			    + ":" + request.getServerPort() //�˿ں� 
			    + request.getContextPath(); //Ӧ�����ƣ����Ӧ������Ϊ
		return requestUrl;
	}
	
	
	public static void sortListStr(List<String> list) {
		Collections.sort(list, new Comparator<String>() {
			public int compare(String o1, String o2) {
				if(o1 == null || o2 == null){
                    return -1;
                }
                if(o1.length() > o2.length()){
                    return -1;
                }
                if(o1.length() < o2.length()){
                    return 1;
                }
                return 0;
			}
		});
	}
}

