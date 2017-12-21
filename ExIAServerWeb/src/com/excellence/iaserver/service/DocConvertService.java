package com.excellence.iaserver.service;

import java.io.File;
/**
 * 格式转换服务
 * @author huangjinyuan
 *
 */
public interface DocConvertService {
	
	/**
	 * 转成html文件
	 * @param filePath
	 * @return
	 */
	public File convertToHtml(String filePath);
	
	/**
	 * 转成pdf文件
	 * @param filePath
	 * @return
	 */
	public File convertToPdf(String filePath);
}
