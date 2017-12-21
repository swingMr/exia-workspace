package com.excellence.iaserver.service;

import java.io.FileNotFoundException;

import org.json.JSONObject;

public interface CalculateService {
	/**
	 * 4.3.1	中文文本分词
	 * @param text
	 * @param patten 
	 * @return
	 * @throws Exception
	 */
	public JSONObject participle(String text, Boolean tagPart, int patten) throws FileNotFoundException;
	
	/**
	 * 4.3.2	关键词和摘要提取
	 * @param title
	 * @param text
	 * @param keyWordLimit
	 * @param abstractLength
	 * @return
	 */
	public JSONObject abs(String title, String text, int keyWordLimit,
			int abstractLength) throws FileNotFoundException;

	/**
	 * 4.3.3	生成指定文本数据的标签
	 * @param title
	 * @param text
	 * @param recDomain
	 * @param recHierarchy
	 * @param sourceUnit
	 * @param publishDate 
	 * @return
	 */
	public JSONObject gentag(String title, String text, boolean recDomain,
			boolean recHierarchy, String sourceUnit, String publishDate) throws FileNotFoundException;
}
