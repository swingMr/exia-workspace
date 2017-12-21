package com.excellence.iaserver.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import org.apache.commons.lang.StringUtils;

/**
 * 扩展的日期工具类
 * @author wangjg
 * @date 2017-9-19
 */
public class DateHandlerUtil {
	/**
	  *日期的年份操作
	  *@param dt		当前日期
	  *@param attr		日期操作的属性(year-年,month-月,day-日,hour-时,minute-分,second-秒)
	  *@param num		日期操作的数值
	  *@param status	日期操作的状态(true-加,false-减)
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