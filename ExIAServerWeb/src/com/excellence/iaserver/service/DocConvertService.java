package com.excellence.iaserver.service;

import java.io.File;
/**
 * ��ʽת������
 * @author huangjinyuan
 *
 */
public interface DocConvertService {
	
	/**
	 * ת��html�ļ�
	 * @param filePath
	 * @return
	 */
	public File convertToHtml(String filePath);
	
	/**
	 * ת��pdf�ļ�
	 * @param filePath
	 * @return
	 */
	public File convertToPdf(String filePath);
}
