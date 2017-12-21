package com.excellence.iaserver.service.impl;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excellence.iaserver.common.util.LibreofficeConverter;
import com.excellence.iaserver.service.DocConvertService;

/**
 * ��ʽת������
 * @author huangjinyuan
 *
 */
public class DocConvertServiceImpl implements DocConvertService {
	
	private static final Log logger = LogFactory.getLog(DocConvertService.class);
	
	@Autowired
	private LibreofficeConverter libreofficeConverter;
	

	public LibreofficeConverter getLibreofficeConverter() {
		return libreofficeConverter;
	}

	public void setLibreofficeConverter(LibreofficeConverter libreofficeConverter) {
		this.libreofficeConverter = libreofficeConverter;
	}

	/**
	 * ת����html
	 */
	public File convertToHtml(String filePath) {
		int index = filePath.lastIndexOf(".");
		String targetFilePath = StringUtils.substring(filePath,0,index) + ".html";
		File destfile = new File(targetFilePath);
		File orgFile = new File(filePath);
		try {
			libreofficeConverter.doConvert(orgFile, destfile);
		} catch (Exception e) {
			logger.error("������Ϣ:" + e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destfile;
	}
	
	/**
	 * ת����pdf
	 */
	public File convertToPdf(String filePath) {
		int index = filePath.lastIndexOf(".");
		String targetFilePath = StringUtils.substring(filePath,0,index) + ".pdf";
		File destfile = new File(targetFilePath);
		File orgFile = new File(filePath);
		try {
			libreofficeConverter.doConvert(orgFile, destfile);
		} catch (Exception e) {
			logger.error("������Ϣ:" + e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destfile;
	}

}
