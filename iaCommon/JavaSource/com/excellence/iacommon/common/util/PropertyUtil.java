package com.excellence.iacommon.common.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Properties�ļ�������
 * @author Huangyb
 * @date 2016-6-16
 * @2014-2016 Excellence
 */
public abstract class PropertyUtil {

	public static String getProperty(String propertyName){
		return System.getProperty(propertyName);
	}
	
	//�޸� ���÷������� - ֵ��д�뵽ָ�����ļ���ȥ��
	public static void storeProperties(String filePath, Properties properties) throws Exception{
		FileOutputStream out = null;
		try{
			out = new FileOutputStream(filePath);
			if(properties!=null){
				properties.store(out, "ϵͳ�����޸�");
			}
		}catch(Exception ex){
			throw ex;
		}finally{
			if(out!=null)out.close();
		}
	}
	
	//��ȡ���ļ��е����м� - ֵ��
	public static Properties loadProperties(String filePath) throws Exception{
		InputStream in = new BufferedInputStream(new FileInputStream(filePath));
		Properties properties = loadProperties(in);
		return properties;
	}
	//��ȡ���ļ��е����м� - ֵ��
	public static Properties loadProperties(InputStream is) throws Exception{
		Properties properties = new Properties();
		properties.load(is);
		return properties;
	}
}
