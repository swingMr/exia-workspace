package com.excellence.iacommon.common.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Properties文件处理工具
 * @author Huangyb
 * @date 2016-6-16
 * @2014-2016 Excellence
 */
public abstract class PropertyUtil {

	public static String getProperty(String propertyName){
		return System.getProperty(propertyName);
	}
	
	//修改 ：该方法将键 - 值对写入到指定的文件中去。
	public static void storeProperties(String filePath, Properties properties) throws Exception{
		FileOutputStream out = null;
		try{
			out = new FileOutputStream(filePath);
			if(properties!=null){
				properties.store(out, "系统配置修改");
			}
		}catch(Exception ex){
			throw ex;
		}finally{
			if(out!=null)out.close();
		}
	}
	
	//获取该文件中的所有键 - 值对
	public static Properties loadProperties(String filePath) throws Exception{
		InputStream in = new BufferedInputStream(new FileInputStream(filePath));
		Properties properties = loadProperties(in);
		return properties;
	}
	//获取该文件中的所有键 - 值对
	public static Properties loadProperties(InputStream is) throws Exception{
		Properties properties = new Properties();
		properties.load(is);
		return properties;
	}
}
