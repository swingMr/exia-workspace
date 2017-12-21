package com.excellence.iaserver.service;

import java.io.FileNotFoundException;

import org.json.JSONObject;

public interface CalculateService {
	/**
	 * 4.3.1	�����ı��ִ�
	 * @param text
	 * @param patten 
	 * @return
	 * @throws Exception
	 */
	public JSONObject participle(String text, Boolean tagPart, int patten) throws FileNotFoundException;
	
	/**
	 * 4.3.2	�ؼ��ʺ�ժҪ��ȡ
	 * @param title
	 * @param text
	 * @param keyWordLimit
	 * @param abstractLength
	 * @return
	 */
	public JSONObject abs(String title, String text, int keyWordLimit,
			int abstractLength) throws FileNotFoundException;

	/**
	 * 4.3.3	����ָ���ı����ݵı�ǩ
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
