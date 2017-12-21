package com.excellence.iacommon.common.util;

import java.io.File;

public abstract class Constant {
	
	//ExIAServer连接配置信息
	public static String ExIAServer_HOST = "10.1.6.63";
	public static int    ExIAServer_PORT = 8001;
	
	// ExKE连接配置信息
	public static String EXKE_HOST = "10.1.6.63";
	public static int    EXKE_PORT = 8001;
	public static String EXKE_ACCOUNT = "admin";
	public static String EXKE_PASSWORD = "123456";
	
	//数据库配置
	public static String DB_HOST = "10.1.6.63";
	public static int DB_PORT = 3306;
	public static String DB_ACCOUNT = "root";
	public static String DB_PASSWORD = "123456";
	public static String DB_NAME = "exia";
	
	//地址本
	public static String Address_HOST = "10.1.6.63";
	public static int    Address_PORT = 8001;
	
	public static String MODEL_NAME = "domain";
	
	public static String MODEL_DIR = System.getProperty("oapath")+File.separator+"exiaserver"+File.separator+"data_dig_tools"+File.separator+"data"+File.separator+"classify";

	public static String[] EXTRATC_FILE_TYPE = {"DOC/DOCX","PDF","TEXT","HTML"};
}
