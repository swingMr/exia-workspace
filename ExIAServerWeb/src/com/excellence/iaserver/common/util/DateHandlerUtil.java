package com.excellence.iaserver.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import org.apache.commons.lang.StringUtils;

/**
 * ��չ�����ڹ�����
 * @author wangjg
 * @date 2017-9-19
 */
public class DateHandlerUtil {
	/**
	  *���ڵ���ݲ���
	  *@param dt		��ǰ����
	  *@param attr		���ڲ���������(year-��,month-��,day-��,hour-ʱ,minute-��,second-��)
	  *@param num		���ڲ�������ֵ
	  *@param status	���ڲ�����״̬(true-��,false-��)
	  */
	 public static Date dateHandler(Date dt,String attr,int num,Boolean status) throws Exception {
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		  Calendar rightNow = Calendar.getInstance();
		  rightNow.setTime(dt);
		  if(StringUtils.equals(attr, "year")){
			  if(status){
				  rightNow.add(Calendar.YEAR, num);
			  }else{
				  rightNow.add(Calendar.YEAR, -num);
			  }
		  }else if(StringUtils.equals(attr, "month")){
			  if(status){
				  rightNow.add(Calendar.MONTH, num);
			  }else{
				  rightNow.add(Calendar.MONTH, -num);
			  }
		  }else if(StringUtils.equals(attr, "day")){
			  if(status){
				  rightNow.add(Calendar.DAY_OF_YEAR, num);
			  }else{
				  rightNow.add(Calendar.DAY_OF_YEAR, -num);
			  }
		  }else if(StringUtils.equals(attr, "hour")){
			  if(status){
				  rightNow.add(Calendar.HOUR_OF_DAY, num);
			  }else{
				  rightNow.add(Calendar.HOUR_OF_DAY, -num);
			  }
		  }else if(StringUtils.equals(attr, "minute")){
			  if(status){
				  rightNow.add(Calendar.MINUTE, num);
			  }else{
				  rightNow.add(Calendar.MINUTE, -num);
			  }
		  }else if(StringUtils.equals(attr, "second")){
			  if(status){
				  rightNow.add(Calendar.SECOND, num);
			  }else{
				  rightNow.add(Calendar.SECOND, -num);
			  }
		  }
		  Date dt1 = rightNow.getTime();
		  return dt1;
	 }
	
}