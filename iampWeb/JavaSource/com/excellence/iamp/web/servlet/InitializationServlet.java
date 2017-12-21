package com.excellence.iamp.web.servlet;

import java.io.File;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.lang.StringUtils;

import com.excellence.common.OAConstant;
import com.excellence.common.config.ConfigHelper;
import com.excellence.iacommon.common.util.Constant;
import com.excellence.iacommon.common.util.PropertyUtil;

/**
 * 
 * @author LiuZeHang
 * @date 2017-07-19
 */
public class InitializationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 9139752603665660685L;
	
	public void init() throws ServletException {
		try {
			 String oaPath =System.getProperty("oapath");
			 String propertiesPath= oaPath + File.separator + "exiaserver" + File.separator +"configration.properties";
			 Properties properties = PropertyUtil.loadProperties(propertiesPath);
			 Constant.ExIAServer_HOST = properties.getProperty("ExIAServer.host");
			 Constant.ExIAServer_PORT = Integer.valueOf(properties.getProperty("ExIAServer.port",Constant.ExIAServer_PORT+""));
			 Constant.EXKE_HOST = properties.getProperty("ExKE.host",Constant.EXKE_HOST);
			 Constant.EXKE_PORT = Integer.valueOf(properties.getProperty("ExKE.port",Constant.EXKE_PORT+""));
			 Constant.EXKE_ACCOUNT = properties.getProperty("ExKE.account",Constant.EXKE_ACCOUNT);
			 Constant.EXKE_PASSWORD = properties.getProperty("ExKE.password",Constant.EXKE_PASSWORD);
			 Constant.DB_HOST = properties.getProperty("DB.host",Constant.DB_HOST);
			 Constant.DB_PORT = Integer.valueOf(properties.getProperty("DB.port",Constant.DB_PORT+""));
			 Constant.DB_PASSWORD = properties.getProperty("DB.password",Constant.DB_PASSWORD);
			 Constant.DB_ACCOUNT = properties.getProperty("DB.account",Constant.DB_ACCOUNT);
			 Constant.DB_NAME = properties.getProperty("DB.name",Constant.DB_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
